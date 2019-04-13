package cn.sd.jrz.jtool.cache;

import cn.sd.jrz.jtool.exception.Throw;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 20:09
 */
public class CacheFactoryBuilder {
    public static <T> T build(Class<T> cls, Class<?>[] classes, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        T o = Throw.get(() -> cls.getConstructor(classes).newInstance(args));
        enhancer.setCallback(new CacheMethodInterceptor<>(o));
        //noinspection unchecked
        return (T) enhancer.create(classes, args);
    }

    public static <T> T build(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        T o = Throw.get(cls::newInstance);
        enhancer.setCallback(new CacheMethodInterceptor<>(o));
        //noinspection unchecked
        return (T) enhancer.create();
    }
}
