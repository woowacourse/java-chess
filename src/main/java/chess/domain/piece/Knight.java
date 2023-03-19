package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends Piece {
    private static final List<Integer> moveY = List.of(2, 1, -1, -2, -2, -1, 1, 2);
    private static final List<Integer> moveX = List.of(1, 2, 2, 1, -1, -2, -2, -1);

    private Knight(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Knight from(final Color color) {
        return new Knight(PieceType.KNIGHT, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final List<Position> positions = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            final Position movePosition = Position.of(moveX.get(index), moveY.get(index));
            final Position movedPosition = source.move(movePosition);
            addPositionIfMovableArea(target, movedPosition, positions);
        }
        return positions;
    }

    private void addPositionIfMovableArea(
            final Position target,
            final Position movedPosition,
            final List<Position> positions
    ) {
        if (target.equals(movedPosition)) {
            positions.add(target);
        }
    }
}
