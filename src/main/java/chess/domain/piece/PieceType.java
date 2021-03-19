package chess.domain.piece;

public enum PieceType {
    KING('k', 0.0),
    QUEEN('q', 9.0),
    BISHOP('b', 3.0),
    KNIGHT('n', 2.5),
    PAWN('p', 1.0),
    ROOK('r', 5.0);

    private final char value;
    private final double score;

    PieceType(char value, double score) {
        this.value = value;
        this.score = score;
    }

    public char getValue() {
        return value;
    }

    public double getScore() {
        return score;
    }
}
