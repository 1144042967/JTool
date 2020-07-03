package cn.sd.jrz.jtool.asynchronous

import kotlinx.coroutines.*
import java.util.function.Supplier

/**
 * @author 江荣展
 * @date 2020/7/2
 */

class Async {
    companion object {
        fun running(runnable: Runnable) {
            GlobalScope.launch { runnable.run() }
        }

        fun running(runnable: suspend () -> Unit) {
            GlobalScope.launch { runnable() }
        }

        fun <T> timeoutGet(second: Long = 20, supplier: Supplier<T>): T? {
            val deferred = GlobalScope.async {
                withTimeoutOrNull(second * 1000) {
                    supplier.get()
                }
            }
            return runBlocking { deferred.await() }
        }

        fun <T> timeoutGet(second: Long = 20, supplier: suspend () -> T): T? {
            val deferred = GlobalScope.async {
                withTimeoutOrNull(second * 1000) {
                    supplier()
                }
            }
            return runBlocking { deferred.await() }
        }

        fun <T> waitGet(supplier: Supplier<T>): Result<T> {
            val result = Result<T>()
            GlobalScope.launch {
                result.value = supplier.get()
            }
            return result
        }

        fun <T> waitGet(supplier: suspend () -> T): Result<T> {
            val result = Result<T>()
            GlobalScope.launch {
                result.value = supplier()
            }
            return result
        }
    }

    class Result<T> {
        private var end = false
        internal var value: T? = null
            set(value) {
                field = value
                end = true
            }

        fun await(second: Long = 20): T? {
            val start = System.currentTimeMillis()
            while (System.currentTimeMillis() - start < second * 1000 && !end) {
                Thread.sleep(300)
            }
            return value
        }
    }
}