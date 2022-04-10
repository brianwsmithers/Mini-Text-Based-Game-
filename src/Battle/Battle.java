package Battle;

import Main.RoomTester;
import Player.Player;
import Monster.Monster;

import java.security.SecureRandom;
import java.util.Scanner;

public class Battle {
    private String item;
    private String input = "";

    Player playerModel;
    Monster monsterModel;

    public Battle(Player playerModel, Monster monsterModel) {
        this.playerModel = playerModel;
        this.monsterModel = monsterModel;
    }

    public void start() {
        monsterModel.attackBoost(threshold());
        Scanner scanner = new Scanner(System.in);
        while (playerModel.getHealthPoints() > 0 && monsterModel.getHealthPoints() > 0) {
            System.out.println("Enter command: ");
            System.out.println("attack");
            System.out.println("use");
            System.out.println("examine");
            System.out.println("equip");
            System.out.println("unequip");
            System.out.println("inventory");
            input = scanner.nextLine();

            switch (inputValidator()) {
                case "attack":
                    attack();
                    break;
                case "use":
                    System.out.println(playerModel.heal(item));
                    break;
                case "examine":
                    System.out.println(monsterModel);
                    break;
                case "equip":
                    System.out.println(playerModel.equipItem(item));
                    break;
                case "unequip":
                    System.out.println(playerModel.unEquipItem(item));
                    break;
                case "inventory":
                    System.out.println(playerModel.getPlayerInventoryController().printItemInventory(playerModel));
                    break;
                default: System.out.println("Invalid command");
            }
        }
    }

    public void attack() {
        System.out.println("Player attacks " + monsterModel.getName() + " for " +
                playerModel.getAttackPoints() + " points!");
        monsterModel.setHealthPoints(monsterModel.getHealthPoints() -
                playerModel.getAttackPoints());

        if (monsterModel.getHealthPoints() > 0) {
            System.out.println(monsterModel.getName() + " attacks player for " +
                    monsterModel.getAttackPoints() + " points!");
            playerModel.setHealthPoints(playerModel.getHealthPoints() -
                    monsterModel.getAttackPoints());
        }
        else {
            System.out.println("The " + monsterModel.getName() + " was defeated!");
        }

        if (playerModel.getHealthPoints() <= 0) {
            System.out.println("The player was defeated!");
            System.out.println("GAME OVER!!!");
            gameOver();
        }
        else {
            System.out.printf("Player HP: %d%nMonster HP: %d%n",
                    playerModel.getHealthPoints(), monsterModel.getHealthPoints());
        }
    }

    public int threshold() {
        SecureRandom sr = new SecureRandom();
        return sr.nextInt(10) + 1;
    }

    public String inputValidator() {
        String[] inputValidator = input.split("\\s+");
        item = "";
        if (inputValidator[0].equalsIgnoreCase("pickup")
                || inputValidator[0].equalsIgnoreCase("inspect")
                || inputValidator[0].equalsIgnoreCase("drop")
                || inputValidator[0].equalsIgnoreCase("use")
                || inputValidator[0].equalsIgnoreCase("equip")
                || inputValidator[0].equalsIgnoreCase("unequip")) {
            input = inputValidator[0];
            // recombine string to pass for method
            for (int i = 1; i < inputValidator.length; i++) {
                item += inputValidator[i] + " ";
            }
            return input;
        }
        return input;
    }

    public void gameOver() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        while (input != 1 || input != 2) {
            System.out.println("Quit (1)or play again? (2)");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    RoomTester rt = new RoomTester();
                    rt.startGame(rt);
                    break;
                default: System.out.println("Invalid input!!");
            }
        }
    }

}
