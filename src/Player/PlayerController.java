package Player;

import Inventory.Inventory;

public class PlayerController {
    private Player model;
    private PlayerView view;

    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
    }

    public void traverseRooms(String direction) {
        view.failedRoomTraversal(model.traverseRooms(direction));
    }

    public int getPlayerRoomNumber() {
        return model.getRoomNumber();
    }

    public Inventory getPlayerInventory() {
        return model.getPlayerInventory();
    }

    public Player getModel() {
        return model;
    }
}
