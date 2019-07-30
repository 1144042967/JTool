package cn.sd.jrz.jtool.proxy.model;

import java.lang.reflect.Method;

/**
 * @author 江荣展
 * @date 2019/7/30
 */
public class DuckInvokeParams<S> {
    private S source;
    private Method sourceMethod;
    private Method duckMethod;
    private Object[] params;

    public DuckInvokeParams(S source, Method sourceMethod, Method duckMethod, Object[] params) {
        this.source = source;
        this.sourceMethod = sourceMethod;
        this.duckMethod = duckMethod;
        this.params = params;
    }

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public Method getSourceMethod() {
        return sourceMethod;
    }

    public void setSourceMethod(Method sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    public Method getDuckMethod() {
        return duckMethod;
    }

    public void setDuckMethod(Method duckMethod) {
        this.duckMethod = duckMethod;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}