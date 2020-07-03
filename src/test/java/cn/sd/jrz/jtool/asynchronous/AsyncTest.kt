package cn.sd.jrz.jtool.asynchronous

import kotlinx.coroutines.delay
import org.junit.Test

/**
 * @author 江荣展
 * @date 2020/7/1
 */

class AsyncTest {
    @Test
    fun runTest() {
        Async.running {
            println("1")
            delay(2000)
            println("2")
        }
        Thread.sleep(3 * 1000)
    }

    @Test
    fun getTest() {
        Async.timeoutGet(1) {
            println("1")
            delay(2000)
            "123"
        }?.let { println(it) }
    }

    @Test
    fun getTest2() {
        Async.timeoutGet(3) {
            println("1")
            delay(2000)
            "123"
        }?.let { println(it) }
    }

    @Test
    fun getTest3() {
        val result = Async.waitGet {
            println("1")
            delay(2000)
            "123"
        }
        println(result.await(1))
    }

    @Test
    fun getTest4() {
        val result = Async.waitGet {
            println("1")
            delay(2000)
            "123"
        }
        result.value
        println(result.await(3))
    }
}