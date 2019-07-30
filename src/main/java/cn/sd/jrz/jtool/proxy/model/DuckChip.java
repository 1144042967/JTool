package cn.sd.jrz.jtool.proxy.model;

import java.util.function.Function;

/**
 * @author 江荣展
 * @date 2019/7/30
 */

public class DuckChip<S> {
    private String methodName;
    private Function<DuckInvokeParams<S>, Object> method;

    public DuckChip(String methodName, Function<DuckInvokeParams<S>, Object> method) {
        this.methodName = methodName;
        this.method = method;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Function<DuckInvokeParams<S>, Object> getMethod() {
        return method;
    }

    public void setMethod(Function<DuckInvokeParams<S>, Object> method) {
        this.method = method;
    }
}