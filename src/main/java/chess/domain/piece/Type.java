package chess.domain.piece;

public enum Type {

    PAWN("P", 1),
    ROOK("R", 5),
    KNIGHT("N", 2.5),
    BISHOP("B", 3),
    QUEEN("Q", 9),
    KING("K", 0),
    EMPTY(".", 0);

    private final String symbol;
    private final double value;

    Type(final String symbol, final double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol(final Side side) {
        if (side == Side.WHITE) {
            return this.symbol.toLowerCase();
        }
        return this.symbol;
    }

    public boolean isKing() {
        return this == KING;
    }

    public double getValue() {
        return value;
    }

    public boolean isPawn() {
        return this == PAWN;
    }
}
