package chess.domain.pieces;

public enum Symbol {

    BISHOP("B", 3),
    KING("K", 0),
    KNIGHT("N", 2.5),
    PAWN("P", 1),
    QUEEN("Q", 9),
    ROOK("R", 5);

    private final String value;
    private final double score;

    Symbol(String value, double score) {
        this.value = value;
        this.score = score;
    }

    public String value() {
        return value;
    }

    public double score() {
        return score;
    }
}
