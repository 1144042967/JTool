package cn.sd.jrz.jtool.boxing.primitive;

import cn.sd.jrz.jtool.function.ShortConsumer;
import cn.sd.jrz.jtool.function.ShortUnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:24
 */
public class JShort implements Comparable<JShort> {
    private short value;

    public JShort() {
    }

    public JShort(short value) {
        this.value = value;
    }

    public short set() {
        return value;
    }

    public void get(short value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Short.toString(this.value);
    }

    @Override
    public int hashCode() {
        return Short.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JShort && value == ((JShort) obj).value;
    }

    @Override
    public int compareTo(JShort that) {
        return Short.compare(this.value, that.value);
    }

    public void map(ShortUnaryOperator operator) {
        value = operator.applyAsShort(value);
    }

    public void peek(ShortConsumer consumer){
        consumer.accept(value);
    }
}
