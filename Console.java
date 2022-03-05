import java.util.Objects;
import java.util.Scanner;

public class Console {

    public void consoleCommand(Player player) {
        String input = "";
        while (!input.equalsIgnoreCase("exit")) {
            Room playersRoom = Objects.requireNonNull(
                    Room.getRoom(player.getRoomNumber()));

            printConsoleCommandMenu();

            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            switch (input) {
                case "travel":
                    traverse(player);
                    break;
                case "explore":
                    printRoomInventory(playersRoom);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Invalid command.");
                    consoleCommand(player);
            }
        }
    }

    // Traverse Room
    private void traverse(Player player) {
        // Ask user where they want to travel and pass input to traverseRooms()
        // in Player class.
        String direction = "";
        System.out.println(
                "Which direction do you want to travel in? (North, South, East, West)");
        Scanner input = new Scanner(System.in);
        direction = input.nextLine();
        player.traverseRooms(direction);
    }

    private void printRoomInventory(Room room) {
        System.out.println(room.printRoomInventory());
    }

    private void printConsoleCommandMenu() {
        String menu = "What do you want to do?";
        printConsoleMenuDivider(menu);
        System.out.printf("%n" +
                "Travel%n" +
                "Explore%n" +
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

}
