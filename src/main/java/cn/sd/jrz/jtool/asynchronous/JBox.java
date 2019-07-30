package cn.sd.jrz.jtool.asynchronous;

import akka.actor.AbstractActor;
import akka.actor.PoisonPill;
import akka.actor.Props;
import cn.sd.jrz.jtool.boxing.primitive.JObject;
import cn.sd.jrz.jtool.function.exception.JRunnable;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 17:14
 */
public class JBox<T> extends AbstractActor {
    private volatile boolean end = false;
    private volatile Object result;

    static <T> Props props(JObject<JBox<T>> object) {
        return Props.create(JBox.class, () -> new JBox<>(object));
    }

    private JBox(JObject<JBox<T>> object) {
        object.set(this);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(any -> {
            result = any;
            end = true;
        }).build();
    }

    /**
     * 等待指定时间后返回结果，可多次调用
     *
     * @param second 指定时间
     * @return 执行结果
     */
    public T await(long second) {
        long waitTime = second * 1000;
        long startTime = System.currentTimeMillis();
        while (true) {
            if (end || System.currentTimeMillis() - startTime >= waitTime) {
                try {
                    //noinspection unchecked
                    return (T) result;
                } finally {
                    try {
                        getSelf().tell(PoisonPill.getInstance(), getSelf());
                    } catch (Exception ignored) {
                    }
                }
            }
            JRunnable.run(() -> Thread.sleep(100));
        }
    }

    /**
     * 等待20秒后返回结果，可多次调用
     *
     * @return 执行结果
     */
    public T await() {
        return await(20);
    }

}
