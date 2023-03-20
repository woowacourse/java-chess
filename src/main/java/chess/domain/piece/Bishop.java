package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class Bishop extends Piece {
    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        validateInvalidColor(targetPiece);
        validateInvalidPosition(source, target);

        return source.calculateBetweenPoints(target);
    }

    private void validateInvalidColor(final Piece targetPiece) {
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    private void validateInvalidPosition(final Position source, final Position target) {
        if (source.isNotLinearFunction(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }
}
