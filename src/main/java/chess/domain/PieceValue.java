package chess.domain;

public enum PieceValue {
    KING("K", 0),
    QUEEN("Q", 9),
    KNIGHT("N", 2.5),
    BISHOP("B", 3),
    ROOK("R", 5),
    PAWN("P", 1);

    private String name;
    private double score;

    PieceValue(String name, double score) {
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
