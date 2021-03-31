package chess.domain.piece;

public enum Symbol {
    PAWN("P"),
    KNIGHT("N"),
    ROOK("R"),
    KING("K"),
    EMPTY("."),
    QUEEN("Q"),
    BISHOP("B");

    private final String symbol;

    Symbol(final String symbol) {
        this.symbol = symbol;
    }

    public String asString() {
        return symbol;
    }
}
