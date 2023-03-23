package chess.domain.piece;

public enum PieceType {
    ROOK("r", 5),
    KNIGHT("n", 2.5),
    BISHOP("b", 3),
    QUEEN("q", 9),
    KING("k", 0),
    PAWN("p", 1),
    EMPTY(".", 0);

    private final String name;
    private final double score;

    PieceType(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
