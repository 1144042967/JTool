package cn.sd.jrz.jtool.function;

import java.util.Objects;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
@FunctionalInterface
public interface FloatConsumer {
    void accept(float value);

    default FloatConsumer andThen(FloatConsumer after) {
        Objects.requireNonNull(after);
        return t -> {
            accept(t);
            after.accept(t);
        };
    }
}
