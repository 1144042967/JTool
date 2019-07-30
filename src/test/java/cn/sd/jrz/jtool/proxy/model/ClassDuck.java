package cn.sd.jrz.jtool.proxy.model;

/**
 * @author 江荣展
 * @date 2019/7/30
 */
public interface ClassDuck {
    int getAge();

    void setAge(int age);

    void sleep(long time, String print);

    void say(String sound);
}
