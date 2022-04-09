import Console.*;
import FileReaders.ReadItems;
import FileReaders.ReadPuzzles;
import FileReaders.ReadRoomConnections;
import FileReaders.ReadRoomDescriptions;
import Inventory.*;
import Player.*;
import Room.*;

public class RoomTester {

    private ReadRoomConnections readRoomConnections;
    private ReadRoomDescriptions readRoomDescriptions;
    private ReadItems readItems;
    private ReadPuzzles readPuzzles;

    public static void main(String[] args) {
        RoomTester rt = new RoomTester();
        rt.loadGameData();
        rt.createRooms(rt);

        // Make new player object and init player to the first room.
        Player player =
                new Player(1, new InventoryController(
                        new Inventory(), new InventoryView()), 10);
        PlayerController playerController = new PlayerController(player, new PlayerView());

        // Display player's init location, location details, and mark it as visited.
        RoomController roomController =
                new RoomController(Room.getRoom(playerController.getPlayerRoomNumber()),
                        new RoomView());
        roomController.printRoomPrompt();
        Room.getRoom(player.getRoomNumber()).setVisited(true);

        // Create console controller to let player enter input into the game
        ConsoleController consoleController =
                new ConsoleController(new Console(), new ConsoleView(),
                        playerController, roomController);
        consoleController.enterCommand();
    }

    public void loadGameData() {
        loadRoomConnections();
        loadRoomDescriptions();
        loadItems();
        loadPuzzles();
    }

    private void loadRoomConnections() {
        // Make object to read in room connections
        readRoomConnections = new ReadRoomConnections(
                "src/RoomConnection");
        readRoomConnections.read(); // read connections
    }

    public ReadRoomConnections getReadRoomConnections() {
        return readRoomConnections;
    }

    public void loadRoomDescriptions() {
        // Make object to read in room descriptions
        readRoomDescriptions = new ReadRoomDescriptions(
                "src/RoomDescription");
        readRoomDescriptions.read(); // read descriptions
    }

    public ReadRoomDescriptions getReadRoomDescriptions() {
        return readRoomDescriptions;
    }

    public void loadItems() {
        // Make object to read in items
        readItems = new ReadItems("src/RoomInventory.txt");
        readItems.read();
    }

    public ReadItems getReadItems() {
        return readItems;
    }

    public void loadPuzzles() {
        // Make object to read in puzzles
        readPuzzles = new ReadPuzzles("src/RoomPuzzle.txt");
        readPuzzles.read();
    }

    public ReadPuzzles getReadPuzzles() {
        return readPuzzles;
    }

    public String formatRoomDescription(String description, int newLineCount, String[] strings) {
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
        return description;
    }

    public void placeItemsInRoom(RoomTester rt, Room room, int i) {
        for (int j = 0; j < rt.getReadItems().getItems().size(); j++) {
            if (rt.getReadItems().getItems().get(j).getItemSpawnLocation() == i) {
                room.getRoomInventory().addItem(rt.getReadItems().getItems().get(j));
                rt.getReadItems().getItems().remove(j);
            }
        }
    }

    public void placePuzzlesInRoom(RoomTester rt, Room room, int i) {
        for (int k = 0; k < rt.getReadPuzzles().getPuzzles().size(); k++) {
            if (rt.getReadPuzzles().getPuzzles().get(k).getPuzzleId() == i) {
                room.getRoomInventory().addPuzzle(rt.getReadPuzzles().getPuzzles().get(k));
                rt.getReadPuzzles().getPuzzles().remove(k);
            }
        }
    }

    public void createRooms(RoomTester rt) {
        // Get room count for for-loop condition
        int numberOfRooms = rt.getReadRoomDescriptions().getCurrentRoom();

        // Read all room data and make a room object for each room.
        // Put each room object in a static hashmap.
        for (int i = 1; i < numberOfRooms + 1; i++) {
            String description = "";
            String[][] roomConnections = rt.getReadRoomConnections().getHashMap().get(i);
            String[][] roomDescription = rt.getReadRoomDescriptions().getHashMap().get(i);
            String roomName = roomDescription[0][0];

            int newLineCount = 0;
            for (String[] strings : roomDescription) {
                // Format the room description code
                description = formatRoomDescription(description, newLineCount, strings);

                // Make new room and add to static hashmap room list
                Room room = new Room(i, roomName, description, roomConnections,
                        new Inventory(), false);

                // Put all items into their respective room.
                placeItemsInRoom(rt, room, i);

                // Put all puzzles into their respective room.
                placePuzzlesInRoom(rt, room, i);

                // Add newly created room to static hashmap that contains all rooms.
                room.addRoom(room);
            }
        }
    }
}
