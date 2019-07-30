package cn.sd.jrz.jtool.function;

import java.util.Objects;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
@FunctionalInterface
public interface BooleanUnaryOperator {

    boolean applyAsBoolean(boolean operand);

    default BooleanUnaryOperator compose(BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return v -> applyAsBoolean(before.applyAsBoolean(v));
    }

    default BooleanUnaryOperator andThen(BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsBoolean(t));
    }

    static BooleanUnaryOperator identity() {
        return t -> t;
    }
}