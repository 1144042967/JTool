package cn.sd.jrz.jtool.boxing.primitive;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
public interface BaseJTuple<T> {
    void map(Function<T, ? extends T> function);

    void peek(Consumer<T> consumer);
}
