package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class Knight extends KnightMovable {
    private Knight(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Knight from(final Color color) {
        return new Knight(PieceType.KNIGHT, color);
    }
}
