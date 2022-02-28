import java.util.HashMap;
import java.util.Objects;

public class Player {

    private int roomNumber;
    private final HashMap<Integer, Boolean> isTraveled = new HashMap<>();

    public Player(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void addRoomToTraveledList(int roomNumber) {
        isTraveled.put(roomNumber, true);
    }

    private String checkIfRoomVisited(int roomNumber) {
        if (isTraveled.containsKey(roomNumber)) {
            return "This place seems familiar...";
        }
        else {
            return "You don't recognize this place.";
        }
    }

    public void roomPrompt(Room room) {
        System.out.println(room.getRoomNumber());
        System.out.println(room.getRoomName());
        System.out.println(checkIfRoomVisited(getRoomNumber()));
        System.out.println(room.getRoomDescription());
    }


    public void traverseRooms(String direction) {
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

                        // Copy room object for the players new room
                        Room newRoom = Objects.requireNonNull(Room.getRoom(newRoomNumber));

                        roomPrompt(newRoom); // Display room details
                        addRoomToTraveledList(getRoomNumber()); // Mark room visited
                        nextPass = true; // Stop iterating
                    }
                }
            }
        }
        if (!nextPass) {
            System.out.println("You can't travel here...");
        }
    }

}
