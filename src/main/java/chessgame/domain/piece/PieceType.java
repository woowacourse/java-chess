package chessgame.domain.piece;

public enum PieceType {
    KING("k", 0),
    QUEEN("q", 9),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    ROOK("r", 5);

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
