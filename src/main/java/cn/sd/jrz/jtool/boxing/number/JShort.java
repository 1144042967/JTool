package cn.sd.jrz.jtool.boxing.number;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:24
 */
public class JShort extends Number implements Comparable<JShort> {
    private short value;

    public JShort() {
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
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
        return obj instanceof JShort && value == ((JShort) obj).shortValue();
    }

    @Override
    public int compareTo(JShort that) {
        return Short.compare(this.value, that.value);
    }
}
