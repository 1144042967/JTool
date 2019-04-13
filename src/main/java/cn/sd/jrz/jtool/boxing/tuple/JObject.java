package cn.sd.jrz.jtool.boxing.tuple;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 18:32
 */
public class JObject<T> {
    private T t;

    public JObject() {
    }

    public JObject(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
