package cn.sd.jrz.jtool.boxing.primitive;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 10:26
 */
public class JTuple<First, Second> implements BaseJTuple<JTuple<First, Second>> {
    private First first;
    private Second second;

    public JTuple() {
    }

    public JTuple(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Second getSecond() {
        return second;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JTuple<?, ?> jTuple = (JTuple<?, ?>) o;
        return Objects.equals(first, jTuple.first) && Objects.equals(second, jTuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public void map(Function<JTuple<First, Second>, ? extends JTuple<First, Second>> function) {
        JTuple<First, Second> tuple = function.apply(this);
        this.first = tuple.first;
        this.second = tuple.second;
    }

    public void mapFirst(UnaryOperator<First> operator) {
        first = operator.apply(first);
    }

    public void mapSecond(UnaryOperator<Second> operator) {
        second = operator.apply(second);
    }

    @Override
    public void peek(Consumer<JTuple<First, Second>> consumer) {
        consumer.accept(this);
    }

    public void peekFirst(Consumer<First> consumer) {
        consumer.accept(first);
    }

    public void peekSecond(Consumer<Second> consumer) {
        consumer.accept(second);
    }
}
