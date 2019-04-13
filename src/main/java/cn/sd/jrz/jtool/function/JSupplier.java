package cn.sd.jrz.jtool.function;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:46
 */
@FunctionalInterface
public interface JSupplier<T> {
    T get() throws Exception;
}