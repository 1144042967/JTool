package cn.sd.jrz.jtool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 江荣展
 * Date: 2019-04-03
 * Time: 10:53
 */
public class OtherTest {
    @Test
    public void arrayInsertTest() {
        List<String> list = Arrays.asList("11", "22", "33");
        list = new ArrayList<>(list);
        list.add(1, "temp");
        list.add(0, "temp2");
        System.out.println(list);
    }
}
