package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public abstract class StraightMovablePiece extends Piece {

    protected StraightMovablePiece(final Color color, final Symbol symbol) {
        super(color, symbol);
    }

    protected void putMovablePositionsByDirection(final Map<Direction, List<Position>> movablePositions,
                                                final Position position,
                                                final Direction direction) {
        final Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }

        final List<Position> positionsToDirection = movablePositions.get(direction);
        positionsToDirection.add(nextPosition);

        putMovablePositionsByDirection(movablePositions, nextPosition, direction);
    }
}
