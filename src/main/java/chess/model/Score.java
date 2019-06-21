package chess.model;

public class Score {
    private final Side side;
    private final double score;

    public Score(final Side side, final double score) {
        this.side = side;
        this.score = score;
    }

    public Side getSide() {
        return side;
    }

    public double getScore() {
        return score;
    }
}
