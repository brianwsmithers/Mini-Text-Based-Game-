package Inventory;

import Item.Item;

import java.util.LinkedList;

public class InventoryController {
    private Inventory model;
    private InventoryView view;

    public InventoryController(Inventory model, InventoryView view) {
        this.model = model;
        this.view = view;
    }

    // TODO why is this not being used?
    public void transferItem(String roomName, String action, LinkedList<Item> inventory2,
                             String item) {
        model.transferItem(inventory2, item);
        //view.itemSuccessfullyInteractedWith(action, item, roomName);
    }

    public Inventory getModel() {
        return model;
    }

    public String itemSuccessfullyInteractedWith(boolean itemFound, String action, String item, String roomName) {
        return view.itemSuccessfullyInteractedWith(itemFound, action, item, roomName);
    }

    public String printItemInventory(Object object) {
        return model.printItemInventory(object);
    }

    public String inspect(String item) {
        return model.inspect(item);
    }
}
