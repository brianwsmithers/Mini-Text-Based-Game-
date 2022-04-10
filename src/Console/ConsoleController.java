package Console;

import Player.PlayerController;
import Room.RoomController;

public class ConsoleController {
    private final Console console;
    private final ConsoleView consoleView;
    private final PlayerController playerController;
    private final RoomController roomController;

    public ConsoleController(Console console, ConsoleView ConsoleView,
                             PlayerController playerController,
                             RoomController roomController) {
        this.console = console;
        this.consoleView = ConsoleView;
        this.playerController = playerController;
        this.roomController = roomController;
    }

    public void enterCommand() {
        while (!console.getInput().equalsIgnoreCase("exit")) {
            consoleView.printConsoleCommandMenu();
            console.enterCommand();

            switch (console.inputValidator()) {
                case "travel":
                    consoleView.whichDirectionToTravel();
                    console.masterTraverse(playerController, roomController);
                    //TODO the consoleView should call roomController.printRoomPrompt
                    roomController.printRoomPrompt(playerController);
                    break;
                case "explore":
                    consoleView.explore(playerController);
                    break;
                case "pickup":
                    boolean itemFound = console.pickUp(console.getItem(), playerController);
                    consoleView.itemInteractionMessage(itemFound, playerController,
                            "pickup", console.getItem());
                    break;
                case "inspect":
                    consoleView.inspectItem(console.getItem(), playerController);
                    break;
                case "drop":
                    itemFound = console.drop(console.getItem(), playerController,
                            roomController);
                    consoleView.itemInteractionMessage(itemFound, playerController,
                            "drop", console.getItem());
                    break;
                case "inventory":
                    consoleView.printInventory(playerController);
                    break;
                case "use":
                    console.heal(console.getItem(), playerController);
                    break;
                case "equip":
                    console.equip(console.getItem(), playerController);
                    break;
                case "unequip":
                    console.unEquipItem(console.getItem(), playerController);
                case "exit":
                    break;
                default: consoleView.invalidCommand();
            }
        }
    }
}
