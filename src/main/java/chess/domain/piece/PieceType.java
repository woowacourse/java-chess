package chess.domain.piece;

public enum PieceType {
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("K"),
    QUEEN("Q"),
    KING("K"),
    PAWN("P"),
    EMPTY(".");

    private final String value;

    PieceType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
