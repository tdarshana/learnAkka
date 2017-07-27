package akka.first.app.mapreduce.java.message;

import java.util.List;

public final class MapData {
    private final List<WordCount> dataList;

    public List<WordCount> getDataList() {
        return dataList;
    }

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
