package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class Bishop extends PieceMovable {
    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }
}
