package cn.sd.jrz.jtool.boxing.primitive;

import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:14
 */
public class JInt implements Comparable<JInt> {
    private int value;

    public JInt() {
    }

    public JInt(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JInt && value == ((JInt) obj).value;
    }

    @Override
    public int compareTo(JInt that) {
        return Integer.compare(this.value, that.value);
    }

    public void map(IntUnaryOperator operator) {
        value = operator.applyAsInt(value);
    }

    public void peek(IntConsumer consumer) {
        consumer.accept(value);
    }
}
