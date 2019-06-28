package chess.domain.chess.unit;

public enum UnitInformation {
    PAWN("P", 1),
    ROOK("R", 5),
    KNIGHT("N", 2.5),
    BISHOP("B", 3),
    QUEEN("Q", 9),
    KING("K", 0);

    private String symbol;
    private double score;

    UnitInformation(String symbol, double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public double score() {
        return score;
    }

    public String symbol() {
        return symbol;
    }
}
