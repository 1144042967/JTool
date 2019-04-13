package cn.sd.jrz.jtool.asynchronous;

import cn.sd.jrz.jtool.exception.Catch;
import org.junit.Test;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 18:08
 */
public class AsyncTest {

    @Test
    public void runTest() {
        Async.run(() -> {
            System.out.println("1");
            Thread.sleep(2000);
            System.out.println("2");
        });

        Catch.run(() -> Thread.sleep(3 * 1000));
    }

    @Test
    public void getTest() {
        JBox<String> box = Async.get(() -> {
            System.out.println("1");
            Thread.sleep(2000);
            return "123";
        });
        String result = box.await(1);
        System.out.println(result);
    }

    @Test
    public void getTest2() {
        JBox<String> box = Async.get(() -> {
            System.out.println("1");
            Thread.sleep(2000);
            return "123";
        });
        String result = box.await(3);
        System.out.println(result);
    }

    @Test
    public void getTest3() {
        JBox<String> box = Async.get(() -> {
            System.out.println("1");
            return "123";
        });
        Catch.run(() -> Thread.sleep(2 * 1000));
        String result = box.await(3);
        System.out.println(result);
    }

    @Test
    public void getTest4() {
        JBox<String> box = Async.get(() -> {
            System.out.println("1");
            return "123";
        });
        Catch.run(() -> Thread.sleep(2 * 1000));
        String result = box.await();
        System.out.println(result);
    }
}