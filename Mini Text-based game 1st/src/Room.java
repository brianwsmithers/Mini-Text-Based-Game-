import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Room {

    private int roomNumber;
    private String roomName;
    private String roomDescription;
    private static StringBuilder sb = new StringBuilder();
    private final String[][] roomConnections;

    private static final HashMap<Integer, Room> listOfRooms = new HashMap<>();

    public Room(int roomNumber, String roomName, String roomDescription, String[][] roomConnections) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
        this.roomName = roomName;
        this.roomDescription = roomDescription;
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

    public String[][] getRoomConnections() {
        return roomConnections;
    }

    public void addRoom(Room room) {
        listOfRooms.put(room.getRoomNumber(), room);
    }

    public static Room getRoom(int roomNumber) {
        return listOfRooms.getOrDefault(roomNumber, null);
    }

    public static StringBuilder printMap() {
        sb.setLength(0);
        for (Map.Entry<Integer, Room> set : listOfRooms.entrySet()) {
            sb.append(set.getValue());
        }
        return sb;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", getRoomNumber(), getRoomName(), getRoomDescription(),
                Arrays.deepToString(roomConnections));
    }
}
