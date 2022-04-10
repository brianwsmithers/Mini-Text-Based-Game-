package Inventory;

import Item.Item;
import Puzzle.Puzzle;
import Monster.Monster;
import java.util.LinkedList;

public class Inventory {

    private final LinkedList<Item> itemInventory = new LinkedList<>();
    private final LinkedList<Puzzle> puzzleInventory = new LinkedList<>();

    // Add object to item inventory for initialization
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    // Add object to puzzle inventory for initialization
    public void addPuzzle(Puzzle puzzle) {
        puzzleInventory.add(puzzle);
    }

    // Transfer between Player.Player, Monster or Room.Room
    public boolean transferItem(LinkedList<Item> inventory2, String item) {
        for (int i = 0; i < inventory2.size(); i++) {
            if (inventory2.get(i).getName().equalsIgnoreCase(item)) {
                // Transfer item from inventory1 to inventory2
                Item tempItem = inventory2.get(i);
                itemInventory.add(tempItem);
                // Remove item from
                inventory2.remove(i);
                return true;
            }
        }
        return false;
    }

    public String inspect(String item) {
        for (Item value : itemInventory) {
            if (value.getName().equalsIgnoreCase(item)) {
                return value.getDescription();
            }
        }
        return "No such item exists in your inventory.";
    }

    // Print room inventory contents
    public String printItemInventory(Object object) {
        StringBuilder roomInventorySB = new StringBuilder();
        String caller = object.getClass().getName();

        if (caller.equalsIgnoreCase("room.room")) {
            if (itemInventory.isEmpty()) {
                return String.format("The room does not have any items...%n");
            }
            else {
                String append = "The room has the following items:";
                roomInventorySB.append(append).append("\n");
                for (Item item : itemInventory) {
                    roomInventorySB.append(item.getName()).append("\n");
                }
            }
        }
        else if (caller.equalsIgnoreCase("player.player")) {
            if (itemInventory.isEmpty()) {
                return String.format("You did not pickup any items yet...%n");
            }
            else {
                String append = "Your inventory has the following items:";
                roomInventorySB.append(append).append("\n");
                for (Item item : itemInventory) {
                    roomInventorySB.append(item.getName()).append("\n");
                }
            }
        }
        return roomInventorySB.toString();
    }

    public LinkedList<Item> getItemInventory() {
        return itemInventory;
    }

    public LinkedList<Puzzle> getPuzzleInventory() {
        return puzzleInventory;
    }
}
