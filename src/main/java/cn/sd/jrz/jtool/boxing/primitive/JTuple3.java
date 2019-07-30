package cn.sd.jrz.jtool.boxing.primitive;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
public class JTuple3<First, Second, Third> implements BaseJTuple<JTuple3<First, Second, Third>> {
    private First first;
    private Second second;
    private Third third;

    public JTuple3() {
    }

    public JTuple3(First first, Second second, Third third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

    public Third getThird() {
        return third;
    }

    public void setThird(Third third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JTuple3<?, ?, ?> tuple = (JTuple3<?, ?, ?>) o;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second) && Objects.equals(third, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public void map(Function<JTuple3<First, Second, Third>, ? extends JTuple3<First, Second, Third>> function) {
        JTuple3<First, Second, Third> tuple = function.apply(this);
        this.first = tuple.first;
        this.second = tuple.second;
        this.third = tuple.third;
    }

    public void mapFirst(UnaryOperator<First> operator) {
        first = operator.apply(first);
    }

    public void mapSecond(UnaryOperator<Second> operator) {
        second = operator.apply(second);
    }

    public void mapThird(UnaryOperator<Third> operator) {
        third = operator.apply(third);
    }

    @Override
    public void peek(Consumer<JTuple3<First, Second, Third>> consumer) {
        consumer.accept(this);
    }

    public void peekFirst(Consumer<First> consumer) {
        consumer.accept(first);
    }

    public void peekSecond(Consumer<Second> consumer) {
        consumer.accept(second);
    }

    public void peekThird(Consumer<Third> consumer) {
        consumer.accept(third);
    }
}
