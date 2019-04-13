package cn.sd.jrz.jtool.boxing.number;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:24
 */
public class JFloat extends Number implements Comparable<JFloat> {
    private float value;

    public JFloat() {
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
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
        return obj instanceof JFloat && value == ((JFloat) obj).floatValue();
    }

    @Override
    public int compareTo(JFloat that) {
        return Float.compare(this.value, that.value);
    }
}
