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
            return "This room seems familiar...";
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


    public void traverse(String direction) {
        // Copy room object
        Room currentRoom = Objects.requireNonNull(Room.getRoom(getRoomNumber()));

        String[][] roomConnections = currentRoom.getRoomConnections();

        // Do not iterate if direction is "-1"
        boolean nextPass = direction.equalsIgnoreCase("-1");

        for (String[] room : roomConnections) {
            if (!nextPass) {
                for (int j = 0; j < room.length; j++) {
                    if (room[j].equalsIgnoreCase(direction)) {
                        int newRoomNumber = Integer.parseInt(room[j - 1]);
                        setRoomNumber(newRoomNumber);
                        nextPass = true;

                        Room newRoom = Objects.requireNonNull(Room.getRoom(newRoomNumber));

                        roomPrompt(newRoom);
                        addRoomToTraveledList(getRoomNumber());
                    }
                }
            }
        }
        if (!nextPass) {
            System.out.println("You can't travel here...");
        }
    }

}
