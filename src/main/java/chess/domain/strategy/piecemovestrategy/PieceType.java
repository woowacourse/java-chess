package chess.domain.strategy.piecemovestrategy;

public enum PieceType {

    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    EMPTY(0),
    ;

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public static boolean isKing(final PieceType pieceType) {
        return pieceType == KING;
    }

    public static boolean isPawn(final PieceType pieceType) {
        return pieceType == PAWN;
    }

    public double getScore() {
        return score;
    }
}
