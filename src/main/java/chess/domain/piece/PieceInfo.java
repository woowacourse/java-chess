package chess.domain.piece;

public enum PieceInfo {
    BISHOP("B", "bishop", 3.0f),
    KING("K", "king", 0.0f),
    KNIGHT("N", "knight", 2.5f),
    PAWN("P", "pawn", 1.0f),
    QUEEN("Q", "queen", 9.0f),
    ROOK("R", "rook", 5.0f);

    private final String name;
    private final String type;
    private final float score;

    PieceInfo(String name, String type, float score) {
        this.name = name;
        this.type = type;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }

    public String getType() {
        return type;
    }
}
