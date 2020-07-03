package cn.sd.jrz.jtool.proxy;

import cn.sd.jrz.jtool.asynchronous.Async;
import cn.sd.jrz.jtool.function.exception.JFunction;
import cn.sd.jrz.jtool.function.exception.JRunnable;
import cn.sd.jrz.jtool.function.exception.JSupplier;
import cn.sd.jrz.jtool.proxy.model.DuckChip;
import cn.sd.jrz.jtool.proxy.model.DuckInvokeParams;
import kotlin.Unit;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author 江荣展
 * @date 2019/7/29
 */
public class ProxyBuilder {

    private static <Target, Source> Target buildFactory(Class<Target> targetClass, JSupplier<Source> sourceSupplier, JFunction<Source, MethodInterceptor> function) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        Source o = sourceSupplier.throwEx().get();
        enhancer.setCallback(function.throwEx().apply(o));
        //noinspection unchecked
        return (Target) enhancer.create();
    }

    public static <T> T catchProxy(Class<T> cls, Class<?>[] classes, Object[] args) {
        return buildFactory(cls, () -> cls.getConstructor(classes).newInstance(args), CacheMethodInterceptor::new);
    }

    public static <T> T catchProxy(Class<T> cls) {
        return buildFactory(cls, cls::newInstance, CacheMethodInterceptor::new);
    }

    private static class CacheMethodInterceptor<T> implements MethodInterceptor {
        private final Map<String, Object> cache = new HashMap<>();
        private T source;

        CacheMethodInterceptor(T o) {
            source = o;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
            if (args == null || args.length == 0) {
                return cache.computeIfAbsent(method.getName(), key -> JSupplier.get(() -> method.invoke(source)));
            } else {
                return JSupplier.get(() -> method.invoke(source, args));
            }
        }
    }

    public static <T> T asyncProxy(Class<T> cls, Class<?>[] classes, Object[] args) {
        return buildFactory(cls, () -> cls.getConstructor(classes).newInstance(args), AsyncMethodInterceptor::new);
    }

    public static <T> T asyncProxy(Class<T> cls) {
        return buildFactory(cls, cls::newInstance, AsyncMethodInterceptor::new);
    }

    private static class AsyncMethodInterceptor<T> implements MethodInterceptor {
        private T source;

        AsyncMethodInterceptor(T o) {
            source = o;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
            if (args == null || args.length == 0) {
                Async.Companion.running(() -> {
                    try {
                        method.invoke(source);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                Async.Companion.running(() -> {
                    try {
                        method.invoke(source, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
            return Unit.INSTANCE;
        }
    }

    @SafeVarargs
    public static <T, S> T duckThrowProxy(Class<T> cls, S source, DuckChip<S>... duckChipArray) {
        return buildFactory(cls, () -> source, s -> new DuckMethodInterceptor<>(s, duckChipArray, (e, str) -> {
            if (e != null) {
                throw new RuntimeException("no such method : " + str, e);
            } else {
                throw new RuntimeException("no such method : " + str);
            }
        }));
    }

    @SafeVarargs
    public static <T, S> T duckCatchProxy(Class<T> cls, S source, DuckChip<S>... duckChipArray) {
        return buildFactory(cls, () -> source, s -> new DuckMethodInterceptor<>(s, duckChipArray, (e, str) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                new RuntimeException("no such method : " + str).printStackTrace();
            }
        }));
    }

    @SafeVarargs
    public static <T, S> T duckIgnoreProxy(Class<T> cls, S source, DuckChip<S>... duckChipArray) {
        return buildFactory(cls, () -> source, s -> new DuckMethodInterceptor<>(s, duckChipArray, (e, str) -> JRunnable.empty()));
    }

    private static class DuckMethodInterceptor<T> implements MethodInterceptor {
        private T source;
        private Map<String, Function<DuckInvokeParams<T>, Object>> duckChipMap = new HashMap<>();
        private Map<String, List<Method>> sourceMethodMap = new HashMap<>();
        private BiConsumer<Exception, String> methodNotSupportHandler;

        DuckMethodInterceptor(T o, DuckChip<T>[] duckChipArray, BiConsumer<Exception, String> methodNotSupportHandler) {
            for (DuckChip<T> chip : duckChipArray) {
                duckChipMap.put(chip.getMethodName(), chip.getMethod());
            }
            source = o;
            buildClassMethod(o.getClass(), sourceMethodMap);
            this.methodNotSupportHandler = methodNotSupportHandler;
        }

        private void buildClassMethod(Class cls, Map<String, List<Method>> sourceMethodMap) {
            if (cls != Object.class) {
                Class superclass = cls.getSuperclass();
                buildClassMethod(superclass, sourceMethodMap);
            }
            Method[] methods = cls.getMethods();
            out:
            for (Method method : methods) {
                method.setAccessible(true);
                List<Method> methodList = sourceMethodMap.computeIfAbsent(method.getName(), k -> new ArrayList<>());
                for (int i = 0; i < methodList.size(); i++) {
                    Method already = methodList.get(i);
                    if (isSimpleMethod(already, method)) {
                        methodList.set(i, method);
                        continue out;
                    }
                }
                methodList.add(method);
            }
        }

        private boolean isSimpleMethod(Method m1, Method m2) {
            if (!Objects.equals(m1.getName(), m2.getName())) {
                return false;
            }
            if (m1.getParameterCount() != m2.getParameterCount()) {
                return false;
            }
            if (m1.getParameterCount() == 0) {
                return true;
            }
            return Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes());
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
            String name = method.getName();
            Method sourceMethod = sourceMethodMap.getOrDefault(method.getName(), new ArrayList<>()).stream().filter(m -> isSimpleMethod(method, m)).findAny().orElse(null);
            Function<DuckInvokeParams<T>, Object> function = duckChipMap.get(name);
            if (function != null) {
                function.apply(new DuckInvokeParams<>(source, sourceMethod, method, args));
            } else if (sourceMethod != null) {
                try {
                    return sourceMethod.invoke(source, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    methodNotSupportHandler.accept(e, name);
                }
            } else {
                methodNotSupportHandler.accept(null, name);
            }
            return null;
        }
    }
}
