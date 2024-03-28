package chess.domain.score;

public class Scores {
    private final Score whiteScore;
    private final Score blackScore;

    public Scores(Score whiteScore, Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }
}
