package cn.sd.jrz.jtool.boxing.number;

/**
 * @author 江荣展
 * Date: 2019-04-08
 * Time: 20:14
 */
public class JInt extends Number implements Comparable<JInt> {
    private int value;

    public JInt() {
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
        return obj instanceof JInt && value == ((JInt) obj).intValue();
    }

    @Override
    public int compareTo(JInt that) {
        return Integer.compare(this.value, that.value);
    }
}
