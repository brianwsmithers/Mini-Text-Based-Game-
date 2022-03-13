public class Item {

    private final String name;
    private final String description;
    private final int itemSpawnLocation;

    public Item(int itemSpawnLocation, String name, String description) {
        this.itemSpawnLocation = itemSpawnLocation;
        this.name = name;
        this.description = description;

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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
