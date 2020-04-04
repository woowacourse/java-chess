package chess.domain.piece;

public enum PieceType {
    KING("K",0.0),
    QUEEN("Q", 9.0),
    KNIGHT("N", 2.5),
    ROOK("R", 5.0),
    BISHOP("B", 3.0),
    PAWN("P", 1.0),
    BLANK(".", 0.0);

    private final String resource;
    private final double score;

    PieceType(String resource, double score) {
        this.resource = resource;
        this.score = score;
    }

    public String getResource() {
        return resource;
    }

    public double getScore() {
        return score;
    }

    public boolean isKing() {
        return this.equals(KING);
    }

    public boolean isPawn() {
        return this.equals(PAWN);
    }
}
