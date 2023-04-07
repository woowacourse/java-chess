package chess.domain.strategy;

import chess.domain.piece.Piece;

public enum PieceType {

    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
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
