package chess.domain;

public class Score {
    private final Color color;
    private final double score;

    public Score(Color color, double score) {
        this.color = color;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public boolean isSameColorWith(Color color) {
        return this.color == color;
    }
}
