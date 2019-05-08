package cn.sd.jrz.jtool.serialize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 12:10
 */
public class SerializeTest {

    @Test
    public void toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("String", "abc");
        map.put("Integer", 123);
        map.put("Float", 123f);
        map.put("Double", 123d);
        map.put("Boolean", true);
        System.out.println(Serialize.toJson(map));
    }

    @Test
    public void fromJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("String", "abc");
        map.put("Integer", 123);
        map.put("Float", 123f);
        map.put("Double", 123d);
        map.put("Boolean", true);
        String x = Serialize.toJson(map);
        Map<String, Object> result = Serialize.fromJson(x);
        System.out.println(result);
        System.out.println(result.getClass());
    }

    @Test
    public void skipTest() {
        Bean bean = new Bean();
        bean.setName("张三");
        bean.setAge(15);
        String s = Serialize.toJson(bean);
        System.out.println(s);

        Bean bean2 = Serialize.fromJson(s, Bean.class);
        System.out.println(bean2);
    }


    public static class Bean {
        @Skip
        private String name;

        private Object age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void fromTest(){
        String s = "{\"aaa\":\"123\"}";
        Object o1 = new Gson().fromJson(s, new Type<HashMap<String, Object>>().getType());
        Object o2 = new Gson().fromJson(s, new Type<Map<String, Object>>().getType());
        Object o3 = new Gson().fromJson(s, new TypeToken<Map<String, Object>>(){}.getType());
        Object o4 = new Gson().fromJson(s, HashMap.class);
        System.out.println(o1.getClass());
        System.out.println(o2.getClass());
        System.out.println(o3.getClass());
        System.out.println(o4.getClass());
    }

}