package cn.sd.jrz.jtool.boxing.number;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:17
 */
public class JDouble extends Number implements Comparable<JDouble> {
    private double value;

    public JDouble() {
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
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return value;
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
        return obj instanceof JDouble && value == ((JDouble) obj).doubleValue();
    }

    @Override
    public int compareTo(JDouble that) {
        return Double.compare(this.value, that.value);
    }
}
