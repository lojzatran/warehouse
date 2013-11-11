package models;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import play.libs.Akka;
import play.libs.Comet;
import play.mvc.Results;

import play.mvc.WebSocket.*;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;


public class ExpeditedOrders extends UntypedActor {
    static List<Out<String>> outs = new ArrayList<Out<String>>();

    static ActorRef defaultActor = Akka.system().actorOf(new Props(ExpeditedOrders.class));

    static {
        Akka.system().scheduler().schedule(
                Duration.create(4, SECONDS),
                Duration.create(5, SECONDS),
                defaultActor, new Order(),
                Akka.system().dispatcher(),
                null
        );
    }

    public static void register(Out<String> out) {
        ExpeditedOrders.outs.add(out);
    }

    public static void unregister(Out<String> out) {
        ExpeditedOrders.outs.remove(out);
    }

    public static void notifyOthers(Out<String> me, String message) {
        for (Out out : outs) {
            if (!out.equals(me)) {
                out.write(message);
            }
        }
    }

    public static void notifyAll(String message) {
        for (Out out : outs) {
            out.write(message);
        }
    }

    @Override
    public void onReceive(Object message) throws Exception {
        Order order = (Order) message;
        notifyAll(order.toString());
    }
}
