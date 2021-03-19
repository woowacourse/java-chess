package chess.domain.piece;

public enum PieceInformation {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    PAWN("p", 1),
    KNIGHT("n", 2.5);

    private final String name;
    private final double score;

    PieceInformation(String name, double score) {
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
