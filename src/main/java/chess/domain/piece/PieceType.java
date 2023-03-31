package chess.domain.piece;

public enum PieceType {
    KING("k"),
    QUEEN("q"),
    KNIGHT("n"),
    BISHOP("b"),
    ROOK("r"),
    PAWN("p"),
    NOPIECE(".");

    private final String symbol;

    PieceType(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
