package cn.sd.jrz.jtool.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 11:11
 */
public class Serialize {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping()
                .setExclusionStrategies(new SkipExclusionStrategy())
                .create();
        try {
            Field factories = Gson.class.getDeclaredField("factories");
            factories.setAccessible(true);
            Object o = factories.get(GSON);
            Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
            for (Class c : declaredClasses) {
                if ("java.util.Collections$UnmodifiableList".equals(c.getName())) {
                    Field listField = c.getDeclaredField("list");
                    listField.setAccessible(true);
                    //noinspection unchecked
                    List<TypeAdapterFactory> list = (List<TypeAdapterFactory>) listField.get(o);
                    int i = list.indexOf(ObjectTypeAdapter.FACTORY);
                    list.set(i, MapTypeAdapter.FACTORY);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toJson(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        return GSON.toJson(o);
    }

    public static <T> T fromJson(String json, TypeToken<T> type) {
        return GSON.fromJson(json, type.getType());
    }

    public static Map<String, Object> fromJson(String json) {
        return GSON.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return GSON.fromJson(json, cls);
    }
}
