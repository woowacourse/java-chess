package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class King extends PieceMovable {
    private King(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static King from(final Color color) {
        return new King(PieceType.KING, color);
    }
}
