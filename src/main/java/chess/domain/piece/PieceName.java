package chess.domain.piece;

public enum PieceName {
    BISHOP("b"),
    KING("k"),
    KNIGHT("n"),
    PAWN("p"),
    QUEEN("q"),
    ROOK("r"),
    NULL_PIECE("."),
    ;

    private final String value;

    PieceName(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
