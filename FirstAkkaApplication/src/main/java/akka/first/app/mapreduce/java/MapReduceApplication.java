package akka.first.app.mapreduce.java;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.first.app.mapreduce.java.actors.PingPongActor;

public class MapReduceApplication {
    public static void main(String[] args) throws Exception {
        /*
        // Chapter 2 : Mapreduce App
        Timeout timeout = new Timeout(Duration.parse("5 seconds"));
        ActorSystem _system = ActorSystem.create("MapReduceApp");
        ActorRef master = _system.actorOf(new Props(MasterActor.class), "Master");
        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog");
        master.tell("Dog is man's best friend");
        master.tell("Dog and Fox belong to the same family");
        Thread.sleep(5000);
        Future<Object> future = Patterns.ask(master, new Result(),  timeout);
        String result = (String) Await.result(future, timeout.duration());
        System.out.println(result);
        _system.shutdown();*/

        /*
        // Chapter 3 : Ping pong App
        ActorSystem _system = ActorSystem.create("BecomeAndUnbecome");
        ActorRef pingPongActor = _system.actorOf(new Props(PingPongActor.class));
        pingPongActor.tell(PingPongActor.PING);
        Thread.sleep(2000);
        _system.shutdown();*/

    }
}
