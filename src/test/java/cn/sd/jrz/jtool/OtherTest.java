package cn.sd.jrz.jtool;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.sd.jrz.jtool.function.exception.JRunnable;
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

    @Test
    public void asynTest() {
        ActorSystem system = ActorSystem.create();
        for (int i = 0; i < 10000000; i++) {
            ActorRef actorRef = system.actorOf(Props.create(SleepActor.class, SleepActor::new));
            actorRef.tell(i, ActorRef.noSender());
            // System.out.println("Tell: " + i);
        }

        JRunnable.run(() -> Thread.sleep(2000000));
    }

    public static class SleepActor extends AbstractActor {

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(Integer.class, any -> {
                        Thread.sleep((10000000 - any) / 3000);
                        System.out.println("Index: " + any + "  " + Thread.currentThread().getName());
                    }).build();
        }
    }
}
