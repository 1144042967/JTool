package cn.sd.jrz.jtool.boxing.primitive;

import cn.sd.jrz.jtool.function.FloatConsumer;
import cn.sd.jrz.jtool.function.FloatUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:24
 */
public class JFloat implements Comparable<JFloat> {
    private float value;

    public JFloat() {
    }

    public JFloat(float value) {
        this.value = value;
    }

    public float get() {
        return value;
    }

    public void set(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }

    @Override
    public int hashCode() {
        return Float.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JFloat && value == ((JFloat) obj).value;
    }

    @Override
    public int compareTo(JFloat that) {
        return Float.compare(this.value, that.value);
    }

    public void map(FloatUnaryOperator operator) {
        value = operator.applyAsFloat(value);
    }

    public void peek(FloatConsumer consumer) {
        consumer.accept(value);
    }
}
