package cn.sd.jrz.jtool.boxing.primitive;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:17
 */
public class JDouble implements Comparable<JDouble> {
    private double value;

    public JDouble() {
    }

    public JDouble(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public void set(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JDouble && value == ((JDouble) obj).value;
    }

    @Override
    public int compareTo(JDouble that) {
        return Double.compare(this.value, that.value);
    }

    public void map(DoubleUnaryOperator operator) {
        value = operator.applyAsDouble(value);
    }

    public void peek(DoubleConsumer consumer) {
        consumer.accept(value);
    }
}
