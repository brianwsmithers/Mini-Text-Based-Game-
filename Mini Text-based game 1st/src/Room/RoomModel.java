package Room;

import java.util.Iterator;
import java.util.Map;

public class RoomModel {

    private int roomNumber;
    private String roomName;
    private String roomDescription;
    private boolean visited;
    private Map<String, String> roomConnections;

    public RoomModel(int roomNumber, String roomName, String roomDescription,
                     boolean visited, Map<String, String> roomConnections) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.visited = visited;
        this.roomConnections = roomConnections;
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

    public String isVisited() {
        if (visited) {
            return "This room seems familiar...";
        }
        else {
            return "You don't recognize this room.";
        }
    }

    public Map<String, String> getRoomConnections() {
        return roomConnections;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n%s%n%s",
                getRoomNumber(), getRoomName(), getRoomDescription(), isVisited());
    }

    public String iterateRoomConnection() {

        for (Map.Entry<String, String> entry : getRoomConnections().entrySet()) {
            System.out.print(entry.getKey() + " " + entry.getValue() + "\n");
        }
        return "";
    }
}
