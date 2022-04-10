package FileReaders;

import Puzzle.Puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadPuzzles extends ReadTextFile {

    private final LinkedList<Puzzle> puzzles = new LinkedList<>();

    private int puzzleId;
    private String puzzleDescription;
    private String puzzleAnswer;
    private int puzzleTriesAvailable;

    public ReadPuzzles(String filePath) {
        super(filePath);
    }

    @Override
    public void read() {
        try {
            File file = new File(getFilePath());

            Scanner scanner = new Scanner(file);

            int counter = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (counter == 0) {
                    puzzleId = Integer.parseInt(line);
                    counter++;
                }
                else if (counter == 1) {
                    puzzleDescription = line;
                    counter++;
                }
                else if (counter == 2) {
                    puzzleAnswer = line;
                    counter++;
                }
                else if (counter == 3) {
                    puzzleTriesAvailable = Integer.parseInt(line);
                    counter = 0;
                    puzzles.add(new Puzzle(puzzleId, puzzleDescription, puzzleAnswer,
                            puzzleTriesAvailable));
                }


            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("The %s file was not found.%n", getFilePath());
        }
    }

    public LinkedList<Puzzle> getPuzzles() {
        return puzzles;
    }
}
