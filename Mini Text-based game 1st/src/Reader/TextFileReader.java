package Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextFileReader {

    private int roomNumber;
    private String roomName;
    private String roomDescription;
    private boolean visited;
    private final Map<String, String> roomConnections = new HashMap<>();

    public TextFileReader() {}

    public void readTextFile() {
        try {
            Scanner scanner = new Scanner(new File("src/RoomTest"));

            int count = 0;
            while (scanner.hasNext()) {
                if (count == 0) {
                    roomNumber = Integer.parseInt(scanner.nextLine());
                    count++;
                }
                else if (count == 1) {
                    roomName = scanner.nextLine();
                    count++;
                }
                else if (count == 2) {
                    roomDescription = scanner.nextLine();
                    count++;
                }
                else if (count == 3) {
                    visited = scanner.nextBoolean();
                    scanner.nextLine();
                    count++;
                }
                else {
                    String nextRoom = scanner.nextLine();
                    String directionalNumber = scanner.nextLine();
                    roomConnections.put(directionalNumber, nextRoom);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public boolean isVisited() {
        return visited;
    }

    public Map<String, String> getRoomConnections() {
        return roomConnections;
    }
}
