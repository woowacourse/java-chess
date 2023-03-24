package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class Rook extends PieceMovable {
    private Rook(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Rook from(final Color color) {
        return new Rook(PieceType.ROOK, color);
    }
}
