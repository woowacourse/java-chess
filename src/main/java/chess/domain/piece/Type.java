package chess.domain.piece;

import chess.domain.Price;

public enum Type {

    QUEEN("Q", Price.from(9)),
    ROOK("R", Price.from(5)),
    BISHOP("B", Price.from(3)),
    KNIGHT("N", Price.from(2.5)),
    PAWN("P", Price.from(1)),
    KING("K", Price.from(0)),
    EMPTY(".", Price.from(0));

    private final String symbol;
    private final Price price;

    Type(final String symbol, final Price price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol(final Side side) {
        if (side == Side.WHITE) {
            return this.symbol.toLowerCase();
        }
        return this.symbol;
    }

    public Price price() {
        return this.price;
    }
}
