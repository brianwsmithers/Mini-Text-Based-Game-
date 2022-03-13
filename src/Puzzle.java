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
}
