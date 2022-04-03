package Console;

import Player.PlayerController;
import Room.RoomController;

public class ConsoleController {
    private final Console model;
    private final ConsoleView view;
    private final PlayerController playerController;
    private final RoomController roomController;

    public ConsoleController(Console model, ConsoleView view,
                             PlayerController playerController, RoomController roomController) {
        this.model = model;
        this.view = view;
        this.playerController = playerController;
        this.roomController = roomController;
    }

    public void enterCommand() {
        while (!model.getInput().equalsIgnoreCase("exit")) {
            view.printConsoleCommandMenu();
            model.enterCommand();

            switch (model.inputValidator()) {
                case "travel":
                    view.whichDirectionToTravel();
                    model.masterTraverse(playerController, roomController);
                    roomController.printRoomPrompt();
                    break;
                case "explore":
                    view.explore(playerController);
                    break;
                case "pickup":
                    boolean itemFound = model.pickUp(model.getItem(), playerController);
                    view.itemInteractionMessage(itemFound,
                            playerController, "pickup", model.getItem());
                    break;
                case "inspect":
                    view.inspectItem(model.getItem(), playerController);
                    break;
                case "drop":
                    /*
                    TODO fix this because it is not dropping any items
                    TODO from my inventory.
                     */
                    itemFound = model.drop(model.getItem(), playerController);
                    view.itemInteractionMessage(itemFound,
                            playerController, "drop", model.getItem());
                    break;
                case "inventory":
                    view.printInventory(playerController);
                    break;
                case "exit":
                    break;
                default: view.invalidCommand();
            }
        }
    }
}
