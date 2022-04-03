package Player;

import Inventory.*;
import Room.Room;
import java.util.Objects;

public class Player {

    private int roomNumber;
    private final InventoryController playerInventoryController;

    public Player(int roomNumber, InventoryController playerInventoryController) {
        this.roomNumber = roomNumber;
        this.playerInventoryController = playerInventoryController;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean traverseRooms(String direction) {
        // Copy room object for the players current room
        Room currentRoom = Objects.requireNonNull(Room.getRoom(getRoomNumber()));

        // Get connections from copied room object
        String[][] roomConnections = currentRoom.getRoomConnections();

        // Do not iterate if direction is "-1"
        boolean nextPass = direction.equalsIgnoreCase("-1");

        for (String[] room : roomConnections) {
            if (!nextPass) {
                for (int j = 0; j < room.length; j++) {
                    // Look for direction (N, S, E, W)
                    if (room[j].equalsIgnoreCase(direction)) {
                        // Get players new room number and assign it to player
                        int newRoomNumber = Integer.parseInt(room[j - 1]);
                        setRoomNumber(newRoomNumber);
                        nextPass = true; // Stop iterating
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public InventoryController getPlayerInventoryController() {
        return playerInventoryController;
    }
}
