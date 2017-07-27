package akka.first.app.mapreduce.java.actors;

import akka.actor.UntypedActor;
import akka.first.app.mapreduce.java.message.MapData;
import akka.first.app.mapreduce.java.message.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MapActor extends UntypedActor {

    private final String[] STOP_WORDS = { "a", "am", "an", "and", "are", "as", "at", "be","do", "go", "if", "in", "is", "it", "of", "on", "the", "to" };
    private final List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    public void onReceive(Object message) throws Exception {
        if(message instanceof String){
            String work = (String) message;
            // map the words in the sentence and send the result to akka.first.app.mapreduce.scala.actors.MasterActor
            getSender().tell(evaluateExpression(work));
        }else
            unhandled(message);
    }

    private MapData evaluateExpression(String line){
        //logic to map the words in the sentences
        List<WordCount> dataList = new ArrayList<WordCount>();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreTokens()){
            String word = parser.nextToken().toLowerCase();
            if (! STOP_WORDS_LIST.contains(word)){
                dataList.add(new WordCount(word, Integer.valueOf(1)));
            }
        }
        return new MapData(dataList);
    }
}
