package cn.sd.jrz.jtool.boxing.primitive;

import cn.sd.jrz.jtool.function.BooleanConsumer;
import cn.sd.jrz.jtool.function.BooleanUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:25
 */
public class JBoolean {
    private boolean value;

    public JBoolean() {
    }

    public JBoolean(boolean value) {
        this.value = value;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof JBoolean && value == ((JBoolean) o).value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

    public void map(BooleanUnaryOperator operator) {
        value = operator.applyAsBoolean(value);
    }

    public void peek(BooleanConsumer consumer) {
        consumer.accept(value);
    }
}
