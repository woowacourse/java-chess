package chess.domain.piece;

public enum PieceNotation {
    PAWN("p"),
    ROOK("r"),
    BISHOP("b"),
    KNIGHT("n"),
    QUEEN("q"),
    KING("k"),
    ;

    private final String value;

    PieceNotation(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
