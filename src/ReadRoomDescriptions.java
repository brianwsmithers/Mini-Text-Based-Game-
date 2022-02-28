import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReadRoomDescriptions extends ReadTextFile {

    private int currentRoom = 0;

    public ReadRoomDescriptions(String filePath) {
        super(filePath);
    }

    @Override
    void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            currentRoom = 0;
            while (scanner.hasNext()) {
                currentRoom++; // init to 1 for hashmap

                String line = scanner.nextLine();

                String[] words = line.split("\\.+");

                // Use word count to determine number of dimensions in 2D array
                int wordCount = words.length;

                // Append periods to room description and remove any spaces.
                for (int i = 1; i < words.length; i++) {
                    String sentence = words[i];
                    words[i] = sentence.stripLeading().stripTrailing() + ".";
                }

                // Init new array that is custom for the number of room connections
                String[][] roomData = new String[1][wordCount];

                LinkedList<String> arrayCopy = new LinkedList<>(List.of(words));

                // Fill 2D array with room description data in order
                for (int i = 0; i < roomData.length; i++) {
                    for (int j = 0; j < roomData[i].length; j++) {
                        roomData[i][j] = arrayCopy.pop();
                    }
                }
                getHashMap().put(currentRoom, roomData);
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }

    public int getCurrentRoom() {
        return currentRoom;
    }
}
