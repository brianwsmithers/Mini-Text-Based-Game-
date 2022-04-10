package Player;

import Inventory.*;
import Item.Item;
import Room.Room;
import java.util.Objects;

public class Player {

    private int roomNumber;
    private final InventoryController playerInventoryController;
    private int healthPoints;
    private int attackPoints;
    private Item weapon;

    public Player(int roomNumber, InventoryController playerInventoryController,
                  int healthPoints) {
        this.roomNumber = roomNumber;
        this.playerInventoryController = playerInventoryController;
        this.healthPoints = healthPoints;
        this.attackPoints = 10;
        // Set hands to default weapon
        setWeapon(new Item(0, "Hands", "Weapon",
                "Your hands", 0, true));
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean traverseRooms(String direction) {
        // Copy room object for the players current room
        Room currentRoom = Objects.requireNonNull(Room.getRoom(getRoomNumber()));

        // Get connections from copied room object
        String[][] roomConnections = currentRoom.getRoomConnections();

        // Do not iterate if direction is "-1"
        boolean nextPass = direction.equalsIgnoreCase("-1");

        for (String[] room : roomConnections) {
            if (!nextPass) {
                for (int j = 0; j < room.length; j++) {
                    // Look for direction (N, S, E, W)
                    if (room[j].equalsIgnoreCase(direction)) {
                        // Get players new room number and assign it to player
                        int newRoomNumber = Integer.parseInt(room[j - 1]);
                        setRoomNumber(newRoomNumber);
                        nextPass = true; // Stop iterating
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String heal(String itemName) {
        for (int i = 0; i < playerInventoryController.getItemInventory().size(); i++) {
            if (playerInventoryController.getItemInventory().get(i).getName()
                    .equalsIgnoreCase(itemName)) {
                // Get item
                Item item = playerInventoryController.getItemInventory().get(i);
                if (item.getItemType().equalsIgnoreCase("health")) {
                    // Add points to users health
                    healthPoints += item.getPoints();
                    // Remove item from inventory
                    playerInventoryController.getItemInventory().remove(i);
                    return String.format("Restored %d health points.%n" +
                            "You now have %d health points.%n", item.getPoints(), healthPoints);
                }
                else {
                    return "This item cannot be used to heal you.\n";
                }
            }
        }
        return "No such item in inventory.\n";
    }

    public String equipItem(String itemName) {
        for (int i = 0; i < playerInventoryController.getItemInventory().size(); i++) {
            if (playerInventoryController.getItemInventory().get(i).getName()
                    .equalsIgnoreCase(itemName)) {
                // Get item
                Item item = playerInventoryController.getItemInventory().get(i);
                if (item.getItemType().equalsIgnoreCase("weapon")) {
                    // Add weapon to users weapon slot
                    setWeapon(item);
                    setAttackPoints(attackPoints + item.getPoints());
                    return "Player equipped " + item.getName() + "\n" +
                            "Attack points equal: " + attackPoints + "\n";


                }
                else {
                    return "This item is not capable of being equipped.\n";
                }
            }
        }
        return "No such item in inventory.\n";
    }

    public String unEquipItem(String itemName) {
        if (weapon.getName().equalsIgnoreCase(itemName) && !weapon.getName()
                .equalsIgnoreCase("hands")) {
            // remove attack points
            setAttackPoints(getAttackPoints() - weapon.getPoints());
            // equip bare hands
            setWeapon(new Item(0, "Hands", "Weapon",
                    "Your hands", 0, true));
            return "Player unequipped " + itemName + "\n" +
                    "Attack points equal: " + attackPoints + "\n";
        }
        if (itemName.equalsIgnoreCase("Hands")) {
            return "You can't remove your hands!\n";
        }
        return "No such item in inventory.\n";
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public Item getWeapon() {
        return weapon;
    }

    public InventoryController getPlayerInventoryController() {
        return playerInventoryController;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
