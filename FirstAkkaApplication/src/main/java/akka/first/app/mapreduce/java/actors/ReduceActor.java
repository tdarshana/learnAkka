package akka.first.app.mapreduce.java.actors;

import akka.actor.UntypedActor;
import akka.first.app.mapreduce.java.message.MapData;
import akka.first.app.mapreduce.java.message.ReduceData;
import akka.first.app.mapreduce.java.message.WordCount;

import java.util.HashMap;
import java.util.List;

public class ReduceActor extends UntypedActor {
    public void onReceive(Object message) throws Exception {
        if(message instanceof MapData){
            MapData mapData = (MapData) message;
            getSender().tell(reduce(mapData.getDataList()));
        } else
            unhandled(message);
    }

    private ReduceData reduce(List<WordCount> dataList){
        HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
        for (WordCount wordCount : dataList){
            if(reducedMap.containsKey(wordCount.getWord())){
                Integer value = (Integer) reducedMap.get(wordCount.getWord());
                value ++;
                reducedMap.put(wordCount.getWord(), value);
            }else {
                reducedMap.put(wordCount.getWord(), Integer.valueOf(1));
            }
        }
        return new ReduceData(reducedMap);
    }
}
