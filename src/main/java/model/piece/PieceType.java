package model.piece;

public enum PieceType {

    BISHOP("b", 3),
    PAWN("p", 1),
    KING("k", 0),
    KNIGHT("n", 2.5),
    QUEEN("q", 9),
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
