package chess.domain.piece;

public enum Type {

    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K"),
    EMPTY(".");

    private final String symbol;

    Type(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(final Side side) {
        if (side == Side.WHITE) {
            return this.symbol.toLowerCase();
        }
        return this.symbol;
    }
}
