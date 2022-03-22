package chess;

public enum Type {
    PAWN("p"),
    BISHOP("b"),
    KNIGHT("n"),
    ROOK("r"),
    QUEEN("q"),
    KING("k");

    private final String symbol;

    Type(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
