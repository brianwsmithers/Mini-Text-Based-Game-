package Console;

import Item.Item;
import Player.*;
import Room.*;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Console {

    private String item;
    private String input = "";

    public void enterCommand() {
        input = "";
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().toLowerCase();
    }

    // Traverse Room.Room
    private void traverse(PlayerController playerController) {
        String direction = "";
        Scanner input = new Scanner(System.in);
        direction = input.nextLine();
        playerController.traverseRooms(direction);
    }

    // This is used to validate input that is done to an object
    public String inputValidator() {
        String[] inputValidator = input.split("\\s+");
        item = "";
        if (inputValidator[0].equalsIgnoreCase("pickup")
                || inputValidator[0].equalsIgnoreCase("inspect")
                || inputValidator[0].equalsIgnoreCase("drop")) {
            input = inputValidator[0];
            // recombine string to pass for method
            for (int i = 1; i < inputValidator.length; i++) {
                item += inputValidator[i] + " ";
            }
            return input;
        }
        return input;
    }

    public String getInput() {
        return input;
    }

    public void masterTraverse(PlayerController playerController,
                               RoomController roomController) {
        // Change rooms if direction is valid
        // Change the room in the model
        traverse(playerController);
        roomController.setModel(
                Room.getRoom(playerController.getPlayerRoomNumber()));
        Room.getRoom(playerController.getPlayerRoomNumber()).setVisited(true);
    }

    public boolean pickUp(String item, PlayerController playerController) {
        // Get players room
        Room playersRoom = Objects.requireNonNull(
                Room.getRoom(playerController.getPlayerRoomNumber()));

        // Get item list
        LinkedList<Item> roomInventory =
                playersRoom.getRoomInventory().getItemInventory();

        // Return true if item is in the room
        return playerController.getPlayerInventoryController().
                getModel().transferItem(roomInventory, item);
    }

    public boolean drop(String item, PlayerController playerController,
                        RoomController roomController) {
        // Get player Inventory
        LinkedList<Item> playerInventoryList =
                playerController.getPlayerInventoryController().getItemInventory();
        // I think this needs to be roomController.getModel.getInventoryController
        return roomController.getModel().getRoomInventory().
                transferItem(playerInventoryList, item);
    }

    public String getItem() {
        return item.stripTrailing();
    }
}
