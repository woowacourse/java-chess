package chess.domain.piece;

public enum PieceType {
    ROOK("R", 5.0),
    BISHOP("B", 3.0),
    KNIGHT("N", 2.5),
    QUEEN("Q", 9.0),
    KING("K", 0.0),
    PAWN("P", 1.0),
    EMPTY(".", 0.0);

    private final String value;
    private final double score;

    PieceType(final String value, final double score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public double getScore() {
        return score;
    }
}
