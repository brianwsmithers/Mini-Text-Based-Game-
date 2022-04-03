package Inventory;

public class InventoryView {

    public String itemSuccessfullyInteractedWith(boolean itemFound, String action,
                                                 String item, String roomName) {
        String message = "";
        String formattedItem = item.substring(0, 1).toUpperCase() + item.substring(1);
        if (itemFound) {
            if (action.equalsIgnoreCase("pickup")) {
                message = String.format("%s has been successfully added to your inventory from the %s.%n",
                        formattedItem, roomName);
            }
            else if (action.equalsIgnoreCase("drop")) {
                message = String.format("%s has been successfully dropped from inventory into the %s.%n",
                        formattedItem, roomName);

            }
            return message;
        }
        else {
            return String.format("No such item exists to %s.%n", action);
        }
    }
}
