package cn.sd.jrz.jtool.boxing.number;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:16
 */
public class JByte extends Number implements Comparable<JByte> {
    private byte value;

    public JByte() {
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
    public int hashCode() {
        return Byte.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JByte && value == ((JByte) obj).byteValue();
    }

    @Override
    public int compareTo(JByte that) {
        return Byte.compare(this.value, that.value);
    }
}
