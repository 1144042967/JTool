package cn.sd.jrz.jtool.asynchronous;

import akka.actor.AbstractActor;
import akka.actor.PoisonPill;
import akka.actor.Props;
import cn.sd.jrz.jtool.boxing.tuple.JObject;
import cn.sd.jrz.jtool.exception.Catch;

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

    public T await(long second) {
        long waitTime = second * 1000;
        long startTime = System.currentTimeMillis();
        while (true) {
            if (end || System.currentTimeMillis() - startTime >= waitTime) {
                try {
                    //noinspection unchecked
                    return (T) result;
                } finally {
                    getSelf().tell(PoisonPill.getInstance(), getSelf());
                }
            }
            Catch.run(() -> Thread.sleep(100));
        }
    }

    public T await() {
        return await(20);
    }

}
