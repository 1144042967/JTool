package cn.sd.jrz.jtool.function.exception;

import java.util.function.Supplier;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:46
 */
@FunctionalInterface
public interface JSupplier<T> {

    static <T> T get(JSupplier<T> supplier) {
        return supplier.ignoreEx(null).get();
    }

    static <T> JSupplier<T> warp(JSupplier<T> supplier) {
        return supplier;
    }

    T get() throws Exception;

    default Supplier<T> catchEx() {
        return catchEx(null);
    }

    default Supplier<T> catchEx(T def) {
        return () -> {
            try {
                return get();
            } catch (Exception e) {
                e.printStackTrace();
                return def;
            }
        };
    }

    default Supplier<T> ignoreEx() {
        return ignoreEx(null);
    }

    default Supplier<T> ignoreEx(T def) {
        return () -> {
            try {
                return get();
            } catch (Exception ignored) {
                return def;
            }
        };
    }

    default Supplier<T> throwEx() {
        return () -> {
            try {
                return get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}