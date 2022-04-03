import Player.*;
import Room.*;

import java.util.Objects;
import java.util.Scanner;

public class Console {

    public void consoleCommand(PlayerController playerController,
                               RoomController roomController) {
        String input = "";
        while (!input.equalsIgnoreCase("exit")) {
            Room playersRoom = Objects.requireNonNull(
                    Room.getRoom(playerController.getPlayerRoomNumber()));

            printConsoleCommandMenu();

            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine().toLowerCase();

            // Check if input is pickup, inspect or drop
            String[] inputValidator = input.split("\\s+");
            String item = "";
            if (inputValidator[0].equalsIgnoreCase("pickup")
                    || inputValidator[0].equalsIgnoreCase("inspect")
                    || inputValidator[0].equalsIgnoreCase("drop")) {
                input = inputValidator[0];
                // recombine string to pass for method
                for (int i = 1; i < inputValidator.length; i++) {
                    item += inputValidator[i] + " ";
                }
            }

            String roomName = playersRoom.getRoomName();

            switch (input) {
                case "travel":
                    // Change rooms if direction is valid
                    traverse(playerController);
                    // Change the room in the model
                    roomController.setModel(
                            Room.getRoom(playerController.getPlayerRoomNumber()));
                    roomController.printRoomDisplay();
                    Room.getRoom(playerController.getPlayerRoomNumber()).setVisited(true);
                    break;
                case "explore":
                    printRoomInventory(playersRoom);
                    break;
                case "pickup":
                    System.out.println(playerController.getPlayerInventory().
                            transferItem(roomName, "pickup",
                                    playersRoom.getRoomInventory().
                                    getItemInventory(), item.stripTrailing()));
                    break;
                case "inspect":
                    System.out.println(playerController.getPlayerInventory().inspect(
                            item.stripTrailing()));
                    break;
                case "drop":
                    System.out.println(playersRoom.getRoomInventory()
                            .transferItem(roomName, "drop",
                                    playerController.getPlayerInventory().
                                    getItemInventory(), item.stripTrailing()));
                    break;
                case "inventory":
                    System.out.println(
                            playerController.getPlayerInventory()
                                    .printItemInventory(playerController.getModel()));
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Invalid command.");
                    consoleCommand(playerController, roomController);
            }
        }
    }

    // Traverse Room.Room
    private void traverse(PlayerController playerController) {
        // Ask user where they want to travel and pass input to traverseRooms()
        // in Player.Player class.
        String direction = "";
        System.out.println(
                "Which direction do you want to travel in? (North, South, East, West)");
        Scanner input = new Scanner(System.in);
        direction = input.nextLine();
        //player.traverseRooms(direction);
        playerController.traverseRooms(direction);
    }

    private void printRoomInventory(Room room) {
        System.out.println(room.getRoomInventory().printItemInventory(room));
    }

    private void printConsoleCommandMenu() {
        String menu = "What do you want to do?";
        printConsoleMenuDivider(menu);
        System.out.printf("%n" +
                "Travel%n" +
                "Explore%n" +
                "Pickup%n" +
                "Inspect%n" +
                "Drop%n" +
                "Inventory%n" +
                "Exit%n");
    }

    // Print divider between menu and game commands
    private void printConsoleMenuDivider(String menu) {
        int menuCharCount = (int) menu.chars().count();
        System.out.println(menu);
        for (int i = 0; i < menuCharCount; i++) {
            System.out.print("-");
        }
    }

}
