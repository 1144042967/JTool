package cn.sd.jrz.jtool.boxing.primitive;

import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
public class JLong implements Comparable<JLong> {
    private long value;

    public JLong() {
    }

    public JLong(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof JLong && this.value == ((JLong) o).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public int compareTo(JLong that) {
        return Long.compare(this.value, that.value);
    }

    public void map(LongUnaryOperator operator) {
        value = operator.applyAsLong(value);
    }

    public void peek(LongConsumer consumer) {
        consumer.accept(value);
    }
}
