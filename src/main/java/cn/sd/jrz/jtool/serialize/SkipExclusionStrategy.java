package cn.sd.jrz.jtool.serialize;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 11:12
 */
class SkipExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        Skip annotation = f.getAnnotation(Skip.class);
        return annotation != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
