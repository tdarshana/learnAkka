package akka.first.app.mapreduce.java.actors;

import akka.actor.UntypedActor;
import akka.first.app.mapreduce.java.message.ReduceData;
import akka.first.app.mapreduce.java.message.Result;

import java.util.HashMap;
import java.util.Map;

public class AggregateActor extends UntypedActor {

    private Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        } else if (message instanceof Result) {
            getSender().tell(finalReducedMap.toString());
        } else {
            unhandled(message);
        }
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
        Integer count = null;
        for (String key : reducedList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reducedList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reducedList.get(key));
            }
        }
    }
}
