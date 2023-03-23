package chess.domain.piece;

public enum PieceType {
    KING("k"),
    QUEEN("q"),
    KNIGHT("n"),
    BISHOP("b"),
    ROOK("r"),
    PAWN("p"),
    NOPIECE(".");

    private String symbol;

    PieceType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
