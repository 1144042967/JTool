package cn.sd.jrz.jtool.function;

import java.util.Objects;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:54
 */
@FunctionalInterface
public interface JFunction<T, R> {

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
}
