import java.util.LinkedList;
import java.util.Scanner;

public class Inventory {

    private final LinkedList<Item> inventory1 = new LinkedList<>();

    //TODO scanner
    private Scanner scanner() {
        return new Scanner(System.in);
    }

    // Add object to inventory for initialization
    public void addItem(Item item) {
        inventory1.add(item);
    }

    // Transfer between Player, Monster or Room
    public String transferItem(String roomName, String action, LinkedList<Item> inventory2, String item) {
        for (int i = 0; i < inventory2.size(); i++) {
            if (inventory2.get(i).getName().equalsIgnoreCase(item)) {
                // Transfer item from inventory1 to inventory2
                Item tempItem = inventory2.get(i);
                inventory1.add(tempItem);
                // Remove item from
                inventory2.remove(i);

                String formattedItem = item.substring(0, 1).toUpperCase() + item.substring(1);
                if (action.equalsIgnoreCase("pickup")) {
                    return String.format("%s has been successfully added to your inventory from the %s.",
                            formattedItem, roomName);
                }
                else if (action.equalsIgnoreCase("drop")) {
                    return String.format("%s has been successfully dropped from inventory into the %s.",
                            formattedItem, roomName);
                }
            }
        }
        return String.format("No such item exists to %s.", action);
    }

    public String inspect(String item) {
        for (Item value : inventory1) {
            if (value.getName().equalsIgnoreCase(item)) {
                return value.getDescription();
            }
        }
        return "No such item exists in your inventory.";
    }

    // Print room inventory contents
    public String printInventory(Object object) {
        StringBuilder roomInventorySB = new StringBuilder();
        String caller = object.getClass().getName();

        if (caller.equalsIgnoreCase("room")) {
            if (inventory1.isEmpty()) {
                return String.format("The room does not have any items...%n");
            }
            else {
                String append = "The room has the following items:";
                roomInventorySB.append(append).append("\n");
                for (Item item : inventory1) {
                    roomInventorySB.append(item.getName()).append("\n");
                }
            }
        }
        else if (caller.equalsIgnoreCase("player")) {
            if (inventory1.isEmpty()) {
                return String.format("You did not pickup any items yet...%n");
            }
            else {
                String append = "Your inventory has the following items:";
                roomInventorySB.append(append).append("\n");
                for (Item item : inventory1) {
                    roomInventorySB.append(item.getName()).append("\n");
                }
            }
        }


        return roomInventorySB.toString();
    }

    public LinkedList<Item> getInventory1() {
        return inventory1;
    }
}
