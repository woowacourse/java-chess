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
    public List<Position> findMoveAblePositions(final Position source, final Position target) {
        final int rowDirection = Integer.compare(target.getRow(), source.getRow());
        final int columnDirection = Integer.compare(target.getColumn(), source.getColumn());

        if (source.calculateRowDistance(target.getRow()) >= 2
                && source.calculateColumnDistance(target.getColumn()) >= 2) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }

        return List.of(Position.of(source.getRow() + rowDirection, source.getColumn() + columnDirection));
    }
}
