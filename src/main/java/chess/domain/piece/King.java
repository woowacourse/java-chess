package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class King extends Piece {
    private King(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static King from(final Color color) {
        return new King(PieceType.KING, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        validateInvalidColor(targetPiece);
        validateInvalidPosition(source, target);

        final int rowDirection = target.getRowDirection(source);
        final int columnDirection = target.getColumnDirection(source);

        return List.of(source.move(rowDirection, columnDirection));
    }

    private void validateInvalidPosition(final Position source, final Position target) {
        final int kingMaxMoveDistance = 1;

        if (source.calculateRowDistance(target) > kingMaxMoveDistance ||
                source.calculateColumnDistance(target) > kingMaxMoveDistance) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }
}
