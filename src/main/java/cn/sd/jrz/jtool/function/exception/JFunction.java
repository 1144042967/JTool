package cn.sd.jrz.jtool.function.exception;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:54
 */
@FunctionalInterface
public interface JFunction<T, R> {

    static <T, R> JFunction<T, R> warp(JFunction<T, R> function) {
        return function;
    }

    R apply(T t) throws Exception;

    default <V> JFunction<V, R> compose(JFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> JFunction<T, V> andThen(JFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> JFunction<T, T> identity() {
        return t -> t;
    }


    default Function<T, R> catchEx() {
        return catchEx(null);
    }

    default Function<T, R> catchEx(R def) {
        return (t) -> {
            try {
                return apply(t);
            } catch (Exception e) {
                e.printStackTrace();
                return def;
            }
        };
    }

    default Function<T, R> ignoreEx() {
        return ignoreEx(null);
    }

    default Function<T, R> ignoreEx(R def) {
        return (T t) -> {
            try {
                return apply(t);
            } catch (Exception ignored) {
                return def;
            }
        };
    }

    default Function<T, R> throwEx() {
        return (T t) -> {
            try {
                return apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
