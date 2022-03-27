package chess.domain.piece;

public enum PieceType {
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    KING("k", 0),
    NONE(".", 0);

    private final String symbol;
    private final double score;

    PieceType(final String symbol, final double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public double calculateScore(final double count) {
        return score * count;
    }

    public String getSymbol() {
        return symbol;
    }
}
