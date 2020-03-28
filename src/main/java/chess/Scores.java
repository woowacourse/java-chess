package chess;

public class Scores {
    private final int whiteScore;
    private final int blackScore;

    public Scores(int whiteScore, int blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }
}
