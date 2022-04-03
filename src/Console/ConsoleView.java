package Console;

import Player.PlayerController;
import Room.Room;

import java.util.Objects;

public class ConsoleView {

    public void printConsoleCommandMenu() {
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

    public void whichDirectionToTravel() {
        System.out.println(
                "Which direction do you want to travel in? (North, South, East, West)");
    }

    public void invalidCommand() {
        System.out.println("Invalid command");
    }

    public void explore(PlayerController playerController) {
        Room playersRoom = Objects.requireNonNull(
                Room.getRoom(playerController.getPlayerRoomNumber()));
        printRoomInventory(playersRoom);
    }

    private void printRoomInventory(Room room) {
        System.out.println(room.getRoomInventory().printItemInventory(room));
    }

    public void inspectItem(String item, PlayerController playerController) {
        System.out.println(playerController.getPlayerInventory().inspect(
                item.stripTrailing()));
    }

    public void drop(String item, PlayerController playerController) {
        Room playersRoom = Objects.requireNonNull(
                Room.getRoom(playerController.getPlayerRoomNumber()));
        String roomName = playersRoom.getRoomName();
        System.out.println(playersRoom.getRoomInventory()
                .transferItem(roomName, "drop",
                        playerController.getPlayerInventory().
                                getItemInventory(), item.stripTrailing()));
    }

    public void printInventory(PlayerController playerController) {
        System.out.println(
                playerController.getPlayerInventory()
                        .printItemInventory(playerController.getModel()));
    }

    public void pickUp(String item, PlayerController playerController) {
        Room playersRoom = Objects.requireNonNull(
                Room.getRoom(playerController.getPlayerRoomNumber()));
        String roomName = playersRoom.getRoomName();
        System.out.println(playerController.getPlayerInventory().
                transferItem(roomName, "pickup",
                        playersRoom.getRoomInventory().
                                getItemInventory(), item.stripTrailing()));
    }
}
