package chess.piece;

public enum PieceType {
    PAWN(1),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    EMPTY(0),
    ;

    public static final int ALL_KING_COUNT = 2;

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public static boolean isKing(final Piece piece) {
        return piece.getPieceType() == KING;
    }

    public static boolean isPawn(final Piece piece) {
        return piece.getPieceType() == PAWN;
    }

    public double getScore() {
        return score;
    }
}
