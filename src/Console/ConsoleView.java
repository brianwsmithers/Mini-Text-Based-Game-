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

    public void itemInteractionMessage(boolean itemFound, PlayerController playerController, String action,
                                       String item) {
        Room playersRoom = Objects.requireNonNull(
                Room.getRoom(playerController.getPlayerRoomNumber()));
        String roomName = playersRoom.getRoomName();

        String message = playerController.getPlayerInventoryController().
                itemSuccessfullyInteractedWith(itemFound, action, item, roomName);

        System.out.println(message);
    }

    public void inspectItem(String item, PlayerController playerController) {
        String message =
                playerController.getPlayerInventoryController().inspect(item.stripTrailing());
        System.out.println(message);
    }

    public void printInventory(PlayerController playerController) {
        System.out.println(playerController.getPlayerInventoryController().
                printItemInventory(playerController.getModel()));
    }
}
