package Room;

import Monster.Monster;
import Player.PlayerController;
import Puzzle.Puzzle;
import Room.Room;

import java.util.Arrays;
import java.util.Map;

public class RoomView {

    public static StringBuilder printMap() {
        Room.sb.setLength(0);
        for (Map.Entry<Integer, Room> set : Room.listOfRooms.entrySet()) {
            Room.sb.append(set.getValue());
        }
        return Room.sb;
    }

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

    public void printRoomPrompt(Room room, PlayerController playerController) {
        System.out.println(room.getRoomNumber());
        System.out.println(room.getRoomName());
        System.out.println(checkIfRoomVisited(room));
        System.out.println(room.getRoomDescription());

        // Check if the room has a puzzle...
        if (!room.getRoomInventory().getPuzzleInventory().isEmpty()) {
            Puzzle.puzzlePrompt(room);
        }
        if(!room.getMonstersInRooms().isEmpty()) {
            Monster.monsterPrompt(room, playerController);
        }
    }
}
