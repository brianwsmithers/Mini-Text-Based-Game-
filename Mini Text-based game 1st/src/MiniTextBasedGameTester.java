import java.util.Scanner;

public class MiniTextBasedGameTester {

    public static void main(String[] args) {

        // Make object to read in room connections
        ReadRoomConnections roomConnections = new ReadRoomConnections(
                "src/RoomConnection");
        roomConnections.read();

        // Make object to read in room descriptions
        ReadRoomDescriptions roomDescriptions = new ReadRoomDescriptions(
                "src/RoomDescription");
        roomDescriptions.read();

        // Init player to the first room.
        Player player = new Player(1);

        // Print location and mark it as visited.
        player.roomPrompt();
        player.addRoomToTraveledList(player.getRoomNumber());

        System.out.println(roomDescriptions.printMap());
//
//        String direction = "";
//        while (!direction.equalsIgnoreCase("-1")) {
//            System.out.println(
//                    "\nWhich room do you want to travel to? (North, South, East, West)");
//            Scanner input = new Scanner(System.in);
//            direction = input.nextLine();
//            player.traverse(direction, roomConnections.getHashMap());
//        }
//        // Exit on "-1"
//        System.out.println("Exiting game...");
    }


}
