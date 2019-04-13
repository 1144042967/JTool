package cn.sd.jrz.jtool.asynchronous;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import cn.sd.jrz.jtool.boxing.tuple.JObject;
import cn.sd.jrz.jtool.function.JRunnable;
import cn.sd.jrz.jtool.function.JSupplier;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 15:50
 */
public class Async {
    private Async() {
    }

    private static final ActorSystem SYSTEM = ActorSystem.create("J-Async-System");

    public static void run(JRunnable runnable) {
        ActorRef actorRef = SYSTEM.actorOf(AsyncActor.props());
        actorRef.tell(runnable, ActorRef.noSender());
    }

    public static <T> JBox<T> get(JSupplier<T> supplier) {
        JObject<JBox<T>> object = new JObject<>();
        ActorRef boxRef = SYSTEM.actorOf(JBox.props(object));
        ActorRef actorRef = SYSTEM.actorOf(AsyncActor.props(boxRef));
        actorRef.tell(supplier, ActorRef.noSender());
        return object.get();
    }
}
