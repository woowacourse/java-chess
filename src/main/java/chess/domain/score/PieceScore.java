package chess.domain.score;

public enum PieceScore {
    KING('k', 0.0),
    QUEEN('q', 9.0),
    BISHOP('b', 3.0),
    KNIGHT('n', 2.5),
    PAWN('p', 1.0),
    ROOK('r', 5.0);

    private final char value;
    private final double score;

    PieceScore(char value, double score) {
        this.value = value;
        this.score = score;
    }

    public char value() {
        return value;
    }

    public double score() {
        return score;
    }
}
