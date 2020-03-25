package chess.domain.piece;

public enum PieceType {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1);

    private String name;
    private double score;

    PieceType(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
