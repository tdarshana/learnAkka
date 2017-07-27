package akka.first.app.mapreduce.java.message;

public final class WordCount {
    private final String word;
    private final Integer count;

    public WordCount(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}
