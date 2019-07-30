package cn.sd.jrz.jtool.function.exception;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:49
 */
@FunctionalInterface
public interface JConsumer<T> {

    static <T> JConsumer<T> warp(JConsumer<T> consumer) {
        return consumer;
    }

    void accept(T t) throws Exception;

    default JConsumer<T> andThen(JConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    default Consumer<T> catchEx() {
        return (T t) -> {
            try {
                accept(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    default Consumer<T> ignoreEx() {
        return (T t) -> {
            try {
                accept(t);
            } catch (Exception ignored) {
            }
        };
    }

    default Consumer<T> throwEx() {
        return (T t) -> {
            try {
                accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
