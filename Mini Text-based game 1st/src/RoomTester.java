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

        int numberOfRooms = readRoomDescriptions.getCurrentRoom();

        String[][] roomDescription;
        String[][] roomConnections;
        StringBuilder sbDescription = new StringBuilder();

        // Read all room data and make a room object for each room.
        // Put each room object in a static hashmap.
        for (int i = 1; i < numberOfRooms + 1; i++) {
            sbDescription.setLength(0);
            roomConnections = readRoomConnections.getHashMap().get(i);
            roomDescription = readRoomDescriptions.getHashMap().get(i);
            String roomName = roomDescription[0][0];

            for (String[] strings : roomDescription) {
                // Start k at 1 to only get room descriptions
                for (int k = 1; k < strings.length; k++) {
                    sbDescription.append(strings[k]);
                }

                Room room = new Room(i, roomName, sbDescription, roomConnections);
                room.addRoom(room);
            }
        }

        // Init player to the first room.
        Player player = new Player(1);

        // Print location and mark it as visited.
        // player.roomPrompt();
        player.addRoomToTraveledList(player.getRoomNumber());

        System.out.println(Room.getRoom(1).toString());

//        String direction = "";
//        while (!direction.equalsIgnoreCase("-1")) {
//            System.out.println(
//                    "\nWhich room do you want to travel to? (North, South, East, West)");
//            Scanner input = new Scanner(System.in);
//            direction = input.nextLine();
//            player.traverse(direction);
//        }
//        // Exit on "-1"
//        System.out.println("Exiting game...");
    }
}
