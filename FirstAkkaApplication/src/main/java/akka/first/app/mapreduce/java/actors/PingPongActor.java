package akka.first.app.mapreduce.java.actors;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class PingPongActor extends UntypedActor {
    public static String PING = "PING";
    public static String PONG = "PONG";
    private int count = 0;

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            if (((String) message).matches(PING)) {
                System.out.println("Ping");
                count++;
                Thread.sleep(100);
                getSelf().tell(PONG);

                getContext().become(new Procedure<Object>() {
                    public void apply(Object message) {
                        if (message instanceof String) {
                            if (((String) message).matches(PONG)) {
                                System.out.println("Pong");
                                count++;
                                try {
                                    Thread.sleep(100);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                getSelf().tell(PING);
                                getContext().unbecome();
                            }
                        }
                    }
                });
                if (count > 10) getContext().stop(self());
            }
        }
    }
}
