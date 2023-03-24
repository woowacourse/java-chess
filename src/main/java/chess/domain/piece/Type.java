package chess.domain.piece;

public enum Type {

    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    PAWN("P", 1),
    KING("K", 0),
    EMPTY(".", 0);

    private final String symbol;
    private final double price;

    Type(final String symbol, final double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol(final Side side) {
        if (side == Side.WHITE) {
            return this.symbol.toLowerCase();
        }
        return this.symbol;
    }

    public double price() {
        return this.price;
    }
}
