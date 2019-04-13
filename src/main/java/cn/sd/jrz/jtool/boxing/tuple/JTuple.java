package cn.sd.jrz.jtool.boxing.tuple;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 10:26
 */
public class JTuple<T, U> {
    private T first;
    private U second;

    public JTuple() {
    }

    public JTuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}
