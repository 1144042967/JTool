package cn.sd.jrz.jtool.proxy;

import cn.sd.jrz.jtool.proxy.model.ClassBean;
import cn.sd.jrz.jtool.proxy.model.ClassDuck;
import cn.sd.jrz.jtool.proxy.model.DuckChip;
import org.junit.Test;

/**
 * @author 江荣展
 * @date 2019/7/30
 */
public class ProxyBuilderTest {

    @Test
    public void catchProxy() {
        ClassBean bean = new ClassBean();
        System.out.println(bean.getAge());
        bean.setAge(15);
        System.out.println(bean.getAge());

        ClassBean bean2 = ProxyBuilder.catchProxy(ClassBean.class);
        System.out.println(bean2.getAge());
        bean2.setAge(15);
        System.out.println(bean2.getAge());
    }

    @Test
    public void catchProxy1() {
        ClassBean bean2 = ProxyBuilder.catchProxy(ClassBean.class, new Class[]{int.class}, new Object[]{20});
        System.out.println(bean2.getAge());
        bean2.setAge(15);
        System.out.println(bean2.getAge());
    }

    @Test
    public void asyncProxy() {
        ClassBean bean = new ClassBean();
        System.out.println("start 1");
        bean.sleep(1,"1秒");
        System.out.println("start 2");
        bean.sleep(2,"2秒");
        System.out.println(" end ");


        ClassBean bean2 = ProxyBuilder.asyncProxy(ClassBean.class);
        System.out.println("start 1");
        bean2.sleep(1,"1秒");
        System.out.println("start 2");
        bean2.sleep(2,"2秒");
        System.out.println(" end ");
    }

    @Test
    public void asyncProxy1() {
        ClassBean bean = new ClassBean();
        System.out.println("start 1");
        bean.sleep(1,"1秒");
        System.out.println("start 2");
        bean.sleep(2,"2秒");
        System.out.println(" end ");


        ClassBean bean2 = ProxyBuilder.asyncProxy(ClassBean.class,new Class[]{Integer.TYPE},new Object[]{10});
        System.out.println("start 1");
        bean2.sleep(1,"1秒");
        System.out.println("start 2");
        bean2.sleep(2,"2秒");
        System.out.println(" end ");
    }

    @Test
    public void duckThrowProxy() {
        DuckChip chip = new DuckChip<ClassBean>("say",
                dip->{
                    Object[] params = dip.getParams();
                    System.out.println("sound "+ params[0]);
                    return null;
                });
        ClassBean source = new ClassBean();
        ClassDuck classDuck = ProxyBuilder.duckThrowProxy(ClassDuck.class, source,chip);
        System.out.println(classDuck.getAge());
        classDuck.sleep(1,"test");
        classDuck.say("duck say");
    }
}