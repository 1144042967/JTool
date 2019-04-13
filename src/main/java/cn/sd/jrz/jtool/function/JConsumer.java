package cn.sd.jrz.jtool.function;

import java.util.Objects;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:49
 */
@FunctionalInterface
public interface JConsumer<T> {

    void accept(T t) throws Exception;

    default JConsumer<T> andThen(JConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
