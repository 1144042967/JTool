package cn.sd.jrz.jtool.boxing.primitive;

import cn.sd.jrz.jtool.function.ByteConsumer;
import cn.sd.jrz.jtool.function.ByteUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:16
 */
public class JByte implements Comparable<JByte> {
    private byte value;

    public JByte() {
    }

    public JByte(byte value) {
        this.value = value;
    }

    public byte get() {
        return value;
    }

    public void set(byte value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Byte.toString(this.value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof JByte && value == ((JByte) o).value;
    }

    @Override
    public int hashCode() {
        return Byte.hashCode(value);
    }

    @Override
    public int compareTo(JByte that) {
        return Byte.compare(this.value, that.value);
    }

    public void map(ByteUnaryOperator operator) {
        value = operator.applyAsByte(value);
    }

    public void peek(ByteConsumer consumer) {
        consumer.accept(value);
    }
}
