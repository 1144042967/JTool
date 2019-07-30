package cn.sd.jrz.jtool.proxy.model;

import cn.sd.jrz.jtool.function.exception.JRunnable;

/**
 * @author 江荣展
 * @date 2019/7/30
 */
public class ClassBean {
    private int age;

    public ClassBean() {
        age = 10;
    }

    public ClassBean(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sleep(long time, String print) {
        JRunnable.run(() -> Thread.sleep(time*1000));
        System.out.println(print);
    }
}
