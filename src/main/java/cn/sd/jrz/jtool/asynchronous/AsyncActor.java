package cn.sd.jrz.jtool.asynchronous;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import cn.sd.jrz.jtool.function.exception.JRunnable;
import cn.sd.jrz.jtool.function.exception.JSupplier;

/**
 * @author 江荣展
 * Date: 2019-04-12
 * Time: 16:26
 */
class AsyncActor extends AbstractActor {
    private ActorRef boxRef;

    static Props props() {
        return Props.create(AsyncActor.class, AsyncActor::new);
    }

    static Props props(ActorRef boxRef) {
        return Props.create(AsyncActor.class, () -> new AsyncActor(boxRef));
    }

    private AsyncActor() {
    }

    private AsyncActor(ActorRef boxRef) {
        this.boxRef = boxRef;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(JSupplier.class, supplier -> {
                    Object o = null;
                    try {
                        o = supplier.get();
                    } catch (Exception ignored) {
                    } finally {
                        boxRef.tell(o, getSelf());
                        getSelf().tell(PoisonPill.getInstance(), getSelf());
                    }
                }).match(JRunnable.class, runnable -> {
                    try {
                        runnable.run();
                    } catch (Exception ignored) {
                    } finally {
                        getSelf().tell(PoisonPill.getInstance(), getSelf());
                    }
                }).build();
    }
}
