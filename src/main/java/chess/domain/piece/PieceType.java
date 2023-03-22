package chess.domain.piece;

public enum PieceType {

    PAWN(1),
    KING(0),
    QUEEN(9),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

}
