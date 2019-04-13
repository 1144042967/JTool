package cn.sd.jrz.jtool.boxing.util;

import cn.sd.jrz.jtool.boxing.number.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 11:11
 */
public class JArrays {

    private JArrays() {
    }

    public static List<JByte> asJList(byte... a) {
        Objects.requireNonNull(a);
        ArrayList<JByte> list = new ArrayList<>(a.length);
        for (byte i : a) {
            list.add(new JByte(i));
        }
        return list;
    }

    public static List<JShort> asJList(short... a) {
        Objects.requireNonNull(a);
        ArrayList<JShort> list = new ArrayList<>(a.length);
        for (short i : a) {
            list.add(new JShort(i));
        }
        return list;
    }

    public static List<JInt> asJList(int... a) {
        Objects.requireNonNull(a);
        ArrayList<JInt> list = new ArrayList<>(a.length);
        for (int i : a) {
            list.add(new JInt(i));
        }
        return list;
    }

    public static List<JFloat> asJList(float... a) {
        Objects.requireNonNull(a);
        ArrayList<JFloat> list = new ArrayList<>(a.length);
        for (float i : a) {
            list.add(new JFloat(i));
        }
        return list;
    }

    public static List<JDouble> asJList(double... a) {
        Objects.requireNonNull(a);
        ArrayList<JDouble> list = new ArrayList<>(a.length);
        for (double i : a) {
            list.add(new JDouble(i));
        }
        return list;
    }

    public static <T, R> List<R> convert(List<T> list, Function<T, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }
}
