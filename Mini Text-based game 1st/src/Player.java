import java.util.HashMap;

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
            return "You don't recognize this room.";
        }
    }

    public void roomPrompt() {
        System.out.println("Player location room: " + getRoomNumber());
        System.out.println(checkIfRoomVisited(getRoomNumber()));
    }

    public void traverse(String direction, HashMap<Integer,
            String[][]> roomConnections) {

        // Copy room connections
        String[][] rooms = roomConnections.get(getRoomNumber());

        // Do not iterate if direction is "-1"
        boolean nextPass = direction.equalsIgnoreCase("-1");

        for (String[] room : rooms) {
            if (!nextPass) {
                for (int j = 0; j < room.length; j++) {
                    if (room[j].equalsIgnoreCase(direction)) {
                        setRoomNumber(Integer.parseInt(room[j - 1]));
                        nextPass = true;

                        roomPrompt();
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
