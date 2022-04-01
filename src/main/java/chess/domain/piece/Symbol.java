package chess.domain.piece;

public enum Symbol {

    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P"),
    EMPTY(".");

    private final String value;

    Symbol(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
