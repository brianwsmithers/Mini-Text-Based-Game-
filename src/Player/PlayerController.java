package Player;

import Inventory.*;

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

    public InventoryController getPlayerInventoryController() {
        return model.getPlayerInventoryController();
    }

    public Player getModel() {
        return model;
    }
}
