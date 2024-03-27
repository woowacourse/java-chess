package chess.domain;

import chess.domain.piece.Piece;

public enum PieceRelation {
    NONE,
    ENEMY,
    PEER;

    public static PieceRelation determine(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece == null) {
            return PieceRelation.NONE;
        }
        if (isSameColor(sourcePiece, targetPiece)) {
            return PieceRelation.PEER;
        }
        return PieceRelation.ENEMY;
    }

    private static boolean isSameColor(final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isColor(sourcePiece.color());
    }

    public boolean isPeer() {
        return this == PEER;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
