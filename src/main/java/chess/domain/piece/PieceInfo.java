package chess.domain.piece;

public enum PieceInfo {
    BISHOP("B", 3.0f),
    KING("K", 0.0f),
    KNIGHT("N", 2.5f),
    PAWN("P", 1.0f),
    QUEEN("Q", 9.0f),
    ROOK("R", 5.0f);

    private final String name;
    private final float score;

    PieceInfo(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }
}
