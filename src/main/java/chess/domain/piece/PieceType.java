package chess.domain.piece;

public enum PieceType {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r",5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    BLANK(".", 0);

    private final String type;
    private final double point;

    PieceType(final String type, final double point) {
        this.type = type;
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public double getPoint() {
        return point;
    }
}
