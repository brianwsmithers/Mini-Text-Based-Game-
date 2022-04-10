package Monster;

import Battle.Battle;
import Player.PlayerController;
import Room.Room;

import java.util.Scanner;

public class Monster {
    private int room;
    private String name;
    private String description;
    private int healthPoints;
    private int attackPoints;
    private int threshold;

    public Monster(int room, String name, String description, int healthPoints,
                   int attackPoints, int threshold) {
        this.room = room;
        this.name = name;
        this.description = description;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.threshold = threshold;
    }

    public int getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getThreshold() {
        return threshold;
    }

    public static void monsterPrompt(Room room, PlayerController playerController) {
        Monster monster = room.getMonstersInRooms().getFirst();
        System.out.println("A " + monster.getName() + " appeared.");

        Scanner scanner = new Scanner(System.in);

        String input = "";
        while (!input.equalsIgnoreCase("ignore")
                && monster.getHealthPoints() > 0) {
            System.out.println("Attack, ignore or examine?");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("attack")) {
                Battle battle = new Battle(playerController.getModel(), monster);
                battle.start();
            }
            else if (input.equalsIgnoreCase("ignore")) {
                room.removeMonster();
            }
            else if (input.equalsIgnoreCase("examine")) {
                //TODO print toString of monster
                System.out.println(monster);
            }
        }
        room.removeMonster();
    }

    public void attackBoost(int amount) {
        if (amount > threshold) {
            attackPoints = attackPoints * 2;
            System.out.println("The " + name + "'s damage has been doubled!");
        }
        else {
            System.out.println("The " + name + " did not get a damage boost.");
        }
    }

    @Override
    public String toString() {
        return description + "\nAttack points: " + attackPoints + "\n";
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
