package domain.piece;

public class Score {
    public static final Score ZERO_SCORE = new Score(0);
    public static final Score QUEEN_SCORE = new Score(9);
    public static final Score ROOK_SCORE = new Score(5);
    public static final Score BISHOP_SCORE = new Score(3);
    public static final Score KNIGHT_SCORE = new Score(2.5);
    public static final Score PAWN_SCORE = new Score(1);

    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public double getScore() {
        return score;
    }
}
