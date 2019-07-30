package cn.sd.jrz.jtool.boxing.primitive;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 18:32
 */
public class JObject<Value> {
    private Value value;

    public JObject() {
    }

    public JObject(Value value) {
        this.value = value;
    }

    public Value get() {
        return value;
    }

    public void set(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JObject<?> jObject = (JObject<?>) o;
        return Objects.equals(value, jObject.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public void map(UnaryOperator<Value> operator) {
        value = operator.apply(value);
    }

    public void peek(Consumer<Value> consumer) {
        consumer.accept(value);
    }
}
