package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

public final class Bishop extends SlidingPiece {
    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }

    protected void validateInvalidPosition(final Position source, final Position target) {
        if (source.isNotLinearFunction(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }
}
