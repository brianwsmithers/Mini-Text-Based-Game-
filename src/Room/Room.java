package Room;

import Inventory.Inventory;
import java.util.HashMap;

public class Room {

    private int roomNumber;
    private final String roomName;
    private final String roomDescription;
    protected static StringBuilder sb = new StringBuilder();
    private final String[][] roomConnections;
    private final Inventory roomInventory;
    private boolean visited;

    // This variable should become its own class and call it the ListOfRooms.
    // The idea is that it is a static database that houses all the rooms.
    protected static final HashMap<Integer, Room> listOfRooms = new HashMap<>();

    // All variables
    public Room(int roomNumber, String roomName, String roomDescription,
                String[][] roomConnections, Inventory roomInventory,
                boolean visited) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomConnections = roomConnections;
        this.roomInventory = roomInventory;
        this.visited = visited;
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

    public Inventory getRoomInventory() {
        return roomInventory;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
