package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FixedMovablePiece extends Piece {

    protected FixedMovablePiece(final Color color, final Symbol symbol) {
        super(color, symbol);
    }

    @Override
    public abstract Map<Direction, List<Position>> getMovablePositions(final Position position);


    protected Map<Direction, List<Position>> initMovablePositions(final List<Direction> directions) {
        final Map<Direction, List<Position>> movablePositions = new HashMap<>();
        for (final Direction direction : directions) {
            movablePositions.put(direction, new ArrayList<>());
        }
        return movablePositions;
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
    }
}
