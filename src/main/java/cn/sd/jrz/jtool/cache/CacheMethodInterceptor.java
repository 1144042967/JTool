package cn.sd.jrz.jtool.cache;

import cn.sd.jrz.jtool.exception.Throw;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 20:29
 */
class CacheMethodInterceptor<T> implements MethodInterceptor {
    private final Map<String, Object> cache = new HashMap<>();
    private T source;

    CacheMethodInterceptor(T o) {
        source = o;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
        if (args == null || args.length == 0) {
            return cache.computeIfAbsent(method.getName(), key -> Throw.get(() -> method.invoke(source)));
        } else {
            return Throw.get(() -> method.invoke(source, args));
        }
    }
}
