import java.util.Arrays;

public class RoomTester {
    public static void main(String[] args) {
        // Make object to read in room connections
        ReadRoomConnections readRoomConnections = new ReadRoomConnections(
                "src/RoomConnection");
        readRoomConnections.read();

        // Make object to read in room descriptions
        ReadRoomDescriptions readRoomDescriptions = new ReadRoomDescriptions(
                "src/RoomDescription");
        readRoomDescriptions.read();

        int numberOfRooms = readRoomDescriptions.getCurrentRoom();

        String[][] roomDescription;
        String[][] roomConnections;
        StringBuilder sbDescription = new StringBuilder();

        for (int i = 1; i < numberOfRooms; i++) {
            roomConnections = readRoomConnections.getHashMap().get(i);
            roomDescription = readRoomDescriptions.getHashMap().get(i);
            String roomName = roomDescription[0][0];

            for (int j = 0; j < roomDescription.length; j++) {
                for (int k = 0; k < roomDescription[j].length; k++) {
                    System.out.println(roomDescription[j][k].toString());
                }
            }

            // Room Description currently does not work
//            Room room = new Room(i, roomName, roomDescription, roomConnections);
//            room.addRoom(room);
        }

        //System.out.println(Room.printMap());
    }
}
