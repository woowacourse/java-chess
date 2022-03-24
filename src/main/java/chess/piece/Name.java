package chess.piece;

public enum Name {
    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    PAWN("P");

    private final String value;

    Name(final String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}
