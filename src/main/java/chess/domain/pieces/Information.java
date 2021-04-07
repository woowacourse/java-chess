package chess.domain.pieces;

public enum Information {
    PAWN("P", 1.0),
    ROOK("R", 5.0),
    KNIGHT("N", 2.5),
    BISHOP("B", 3.0),
    QUEEN("Q", 9.0),
    KING("K", 0.0);

    private final String initial;
    private final Double score;

    Information(final String initial, final Double score) {
        this.initial = initial;
        this.score = score;
    }

    public String initial() {
        return initial;
    }

    public Double score() {
        return score;
    }
}
