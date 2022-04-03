package Room;

import Puzzle.Puzzle;

import java.util.Arrays;
import java.util.Map;

public class RoomView {

    /**
     * Makes a list of rooms with a StringBuilder object.
     * @return Returns the StringBuilder object.
     */
    public static StringBuilder printMap() {
        Room.sb.setLength(0);
        for (Map.Entry<Integer, Room> set : Room.listOfRooms.entrySet()) {
            Room.sb.append(set.getValue());
        }
        return Room.sb;
    }

    /**
     * Gets the details of a room.
     * @param room object is necessary to pull model data.
     * @return a String with the room number, room name, room description and room
     * connections.
     */
    public String toString(Room room) {
        return String.format("%s %s %s %s", room.getRoomNumber(), room.getRoomName(),
                room.getRoomDescription(),
                Arrays.deepToString(room.getRoomConnections()));
    }

    private String checkIfRoomVisited(Room room) {
        if (room.isVisited()) {
            return "This place seems familiar...";
        }
        else {
            return "You don't recognize this place.";
        }
    }

    public void printRoomPrompt(Room room) {
        System.out.println(room.getRoomNumber());
        System.out.println(room.getRoomName());
        System.out.println(checkIfRoomVisited(room));
        System.out.println(room.getRoomDescription());

        // Check if the room has a puzzle...
        if (!room.getRoomInventory().getPuzzleInventory().isEmpty()) {
            Puzzle.puzzlePrompt(room);
        }
    }
}
