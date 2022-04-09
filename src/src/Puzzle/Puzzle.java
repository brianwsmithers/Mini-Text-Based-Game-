package Puzzle;

import Room.Room;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Puzzle {

    int puzzleId;
    String puzzleDescription;
    String puzzleAnswer;
    int puzzleTriesAvailable;

    public Puzzle(int puzzleId, String puzzleDescription, String puzzleAnswer,
                  int puzzleTries) {
        this.puzzleId = puzzleId;
        this.puzzleDescription = puzzleDescription;
        this.puzzleAnswer = puzzleAnswer;
        this.puzzleTriesAvailable = puzzleTries;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public String getPuzzleAnswer() {
        return puzzleAnswer;
    }

    public int getPuzzleTriesAvailable() {
        return puzzleTriesAvailable;
    }

    public static void puzzlePrompt(Room room) {
        System.out.println("");
        // Display puzzle description
        System.out.println("Puzzle: " + room.getRoomInventory().getPuzzleInventory()
                .get(0).getPuzzleDescription());

        int attempts = room.getRoomInventory().getPuzzleInventory().get(0)
                .getPuzzleTriesAvailable();
        boolean solved = false;
        while (attempts != 0 || !solved) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(room.getRoomInventory().getPuzzleInventory()
                        .get(0).getPuzzleAnswer())) {
                    solved = true;
                    attempts = 0;
                    System.out.println("You solved the puzzle correctly!");
                    room.getRoomInventory().getPuzzleInventory().remove(0);

                }
                else {
                    attempts--;
                    if (attempts > 0) {
                        System.out.printf("The answer you provided is wrong, you still have " +
                                "%s tries left.", attempts);
                        System.out.println(" Try one more time.");
                    }
                    else {
                        System.out.println("Failed to solve.");
                        System.out.println();
                        solved = true;
                    }
                }
            } catch (InputMismatchException ex) {
                ex.printStackTrace();
            }
        }
    }
}
