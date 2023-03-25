package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class Queen extends PieceMovable {
    private Queen(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Queen from(final Color color) {
        return new Queen(PieceType.QUEEN, color);
    }
}
