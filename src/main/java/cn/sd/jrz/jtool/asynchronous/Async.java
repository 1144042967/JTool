package cn.sd.jrz.jtool.asynchronous;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import cn.sd.jrz.jtool.boxing.primitive.JObject;
import cn.sd.jrz.jtool.function.exception.JRunnable;
import cn.sd.jrz.jtool.function.exception.JSupplier;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 15:50
 */
public class Async {
    private Async() {
    }

    private static final ActorSystem SYSTEM = ActorSystem.create("J-Async-System");

    /**
     * 异步执行方法
     *
     * @param runnable 方法
     */
    public static void run(JRunnable runnable) {
        ActorRef actorRef = SYSTEM.actorOf(AsyncActor.props());
        actorRef.tell(runnable, ActorRef.noSender());
    }

    /**
     * 异步执行方法，并获得可操作结果
     *
     * @param supplier 方法
     * @param <T>      返回结果类型
     * @return 可等待执行结束对象
     */
    public static <T> JBox<T> get(JSupplier<T> supplier) {
        JObject<JBox<T>> object = new JObject<>();
        ActorRef boxRef = SYSTEM.actorOf(JBox.props(object));
        ActorRef actorRef = SYSTEM.actorOf(AsyncActor.props(boxRef));
        actorRef.tell(supplier, ActorRef.noSender());
        return object.get();
    }
}
