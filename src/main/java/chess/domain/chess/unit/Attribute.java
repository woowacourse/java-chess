package chess.domain.chess.unit;

public enum Attribute {
    KING("K", 0),
    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    PAWN("P", 1);

    private final String symbol;
    private final double score;

    Attribute(final String symbol, final double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getScore() {
        return score;
    }
}
