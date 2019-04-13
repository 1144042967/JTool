package cn.sd.jrz.jtool.exception;

import cn.sd.jrz.jtool.function.JConsumer;
import cn.sd.jrz.jtool.function.JFunction;
import cn.sd.jrz.jtool.function.JRunnable;
import cn.sd.jrz.jtool.function.JSupplier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author JRZ
 * @since 1.0
 */
public class Catch {
    private Catch() {
    }

    public static void run(JRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }

    public static Runnable warp(JRunnable runnable) {
        return () -> run(runnable);
    }


    public static <T> T get(JSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> Supplier<T> warp(JSupplier<T> supplier) {
        return () -> get(supplier);
    }

    public static <T> Consumer<T> warp(JConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception ignored) {
            }
        };
    }

    public static <T, R> Function<T, R> warp(JFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                return null;
            }
        };
    }
}
