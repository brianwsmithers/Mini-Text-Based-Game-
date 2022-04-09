package Item;

public class Item {

    private final int itemSpawnLocation;
    private final String name;
    private final String itemType;
    private final String description;
    // points can be damage or health depending on item type
    // weapon -> damage, health -> health
    private final int points;
    private final boolean capabilityToEquip;

    public Item(int itemSpawnLocation, String name, String itemType, String description,
                int points, boolean capabilityToEquip) {
        this.itemSpawnLocation = itemSpawnLocation;
        this.name = name;
        this.itemType = itemType;
        this.description = description;
        this.points = points;
        this.capabilityToEquip = capabilityToEquip;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getItemSpawnLocation() {
        return itemSpawnLocation;
    }

    public String getItemType() {
        return itemType;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Item.Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
