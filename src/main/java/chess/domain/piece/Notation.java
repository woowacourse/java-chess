package chess.domain.piece;

public enum Notation {
    PAWN("p", 1),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    QUEEN("q", 9),
    KING("k", 0),
    ;

    private final String value;
    private final double score;

    Notation(final String value, final double score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public double getScore() {
        return score;
    }
}
