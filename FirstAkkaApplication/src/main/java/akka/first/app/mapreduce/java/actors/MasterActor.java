package akka.first.app.mapreduce.java.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.first.app.mapreduce.java.message.MapData;
import akka.first.app.mapreduce.java.message.ReduceData;
import akka.first.app.mapreduce.java.message.Result;
import akka.first.app.mapreduce.java.message.*;
import akka.first.app.mapreduce.java.actors.ReduceActor;
import akka.routing.RoundRobinRouter;

public class MasterActor extends UntypedActor {

    private ActorRef mapActor = getContext().actorOf(new Props(MapActor.class). withRouter(new RoundRobinRouter(5)), "map" );
    private ActorRef reduceActor = getContext().actorOf(new Props(ReduceActor.class). withRouter(new RoundRobinRouter(5)), "reduce");
    private ActorRef aggregateActor = getContext().actorOf( new Props(AggregateActor.class), "aggregate");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message,getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message,getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message);
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext());
        } else
            unhandled(message);
    }
}
