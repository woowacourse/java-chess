package chessgame.domain.piece;

public enum PieceType {

    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    EMPTY(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public double score() {
        return score;
    }
}
