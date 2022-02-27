import java.util.*;

public abstract class ReadTextFile {

    private final String filePath;
    private final HashMap<Integer, String[][]> hashMap = new HashMap<>();
    private final StringBuilder sb = new StringBuilder();

    public ReadTextFile(String filePath) {
        this.filePath = filePath;
    }

    abstract void read();

    public StringBuilder printMap() {
        sb.setLength(0);
        for (Map.Entry<Integer, String[][]> set : hashMap.entrySet()) {
            sb.append(set.getKey()).append(" ").append(
                    Arrays.deepToString(set.getValue())).append("\n");
        }
        return sb;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, String[][]> getHashMap() {
        return hashMap;
    }
}
