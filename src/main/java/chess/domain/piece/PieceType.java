package chess.domain.piece;

public enum PieceType {
    KING("k", 0.0),
    QUEEN("q", 9.0),
    ROOK("r", 5.0),
    KNIGHT("n", 2.5),
    BISHOP("b", 3.0),
    PAWN("p", 1.0),
    INITIAL_PAWN("p", 1.0);

    private final String name;
    private final Double score;

    PieceType(final String name, final Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }
}
