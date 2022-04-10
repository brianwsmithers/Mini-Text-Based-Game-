package FileReaders;

import Item.Item;
import Monster.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadMonsters extends ReadTextFile {

    LinkedList<Monster> monsters = new LinkedList<>();

    public ReadMonsters(String filePath) {
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

                monsters.add(new Monster(Integer.parseInt(words[0].strip()),
                        words[1].strip(), words[2].strip() + ".",
                        Integer.parseInt(words[3].strip()),
                        Integer.parseInt(words[4].strip()),
                        Integer.parseInt(words[5].strip())));
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }

    public LinkedList<Monster> getMonsters() {
        return monsters;
    }
}
