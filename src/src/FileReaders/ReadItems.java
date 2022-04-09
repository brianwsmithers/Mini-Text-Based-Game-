package FileReaders;

import Item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadItems extends ReadTextFile {

    LinkedList<Item> items = new LinkedList<>();

    public ReadItems(String filePath) {
        super(filePath);
    }

    @Override
    public void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                String[] words = line.split("\\.+");

                items.add(new Item(
                        Integer.parseInt(words[0]),
                        words[1].stripLeading(),
                        words[2].stripLeading(),
                        words[3].stripLeading(),
                        Integer.parseInt(words[4].stripLeading()),
                        Boolean.parseBoolean(words[5])));
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }

    public LinkedList<Item> getItems() {
        return items;
    }
}
