import java.util.Objects;
import java.util.Scanner;

public class RoomTester {
    public static void main(String[] args) {
        // Make object to read in room connections
        ReadRoomConnections readRoomConnections = new ReadRoomConnections(
                "src/RoomConnection");
        readRoomConnections.read(); // read connections

        // Make object to read in room descriptions
        ReadRoomDescriptions readRoomDescriptions = new ReadRoomDescriptions(
                "src/RoomDescription");
        readRoomDescriptions.read(); // read descriptions

        // Get room count for for-loop condition
        int numberOfRooms = readRoomDescriptions.getCurrentRoom();

        // Read all room data and make a room object for each room.
        // Put each room object in a static hashmap.
        for (int i = 1; i < numberOfRooms + 1; i++) {
            String description = "";
            String[][] roomConnections = readRoomConnections.getHashMap().get(i);
            String[][] roomDescription = readRoomDescriptions.getHashMap().get(i);
            String roomName = roomDescription[0][0];

            int newLineCount = 0;
            for (String[] strings : roomDescription) {
                // Start k at 1 to only get room descriptions
                for (int k = 1; k < strings.length; k++) {
                    // Code below is used for spacing out the multi-line room descriptions.
                    String newString = strings[k];
                    if (newLineCount == 0 && k != 1
                            && description.chars().count() + newString.chars().count() > 120) {
                        description += "\n" + strings[k] + " ";
                        newLineCount++;
                    }
                    else if (description.chars().count() + newString.chars().count() > 240
                    && description.chars().count() + newString.chars().count() >= 130) {
                        description += "\n" + strings[k];
                    }
                    else {
                        description += strings[k] + " ";
                    }
                }

                // Make new room and add to static hashmap room list
                Room room = new Room(i, roomName, description, roomConnections);
                room.addRoom(room);
            }
        }

        // Make new player object and init player to the first room.
        Player player = new Player(1);

        // Display player's init location, location details, and mark it as visited.
        Room startingRoom = Objects.requireNonNull(Room.getRoom(player.getRoomNumber()));
        player.roomPrompt(startingRoom);
        player.addRoomToTraveledList(player.getRoomNumber());

        // Ask user where they want to travel and pass input to traverseRooms() in Player class.
        String direction = "";
        while (!direction.equalsIgnoreCase("-1")) {
            System.out.println(
                    "Which direction do you want to travel in? (North, South, East, West)");
            Scanner input = new Scanner(System.in);
            direction = input.nextLine();
            player.traverseRooms(direction);
        }
        // Exit on "-1"
        System.out.println("Exiting game...");
    }
}
