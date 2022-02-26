import java.util.*;

public abstract class ReadTextFile {

    private final String filePath;
    private final HashMap<Integer, String[][]> hashMap = new HashMap<>();

    public ReadTextFile(String filePath) {
        this.filePath = filePath;
    }

    abstract void read();

    public void printMap() {
        for (Map.Entry<Integer, String[][]> set : hashMap.entrySet()) {
            System.out.println(set.getKey() + " " + Arrays.deepToString(set.getValue()));
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, String[][]> getHashMap() {
        return hashMap;
    }
}
