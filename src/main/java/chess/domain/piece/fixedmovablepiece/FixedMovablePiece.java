package chess.domain.piece.fixedmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
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

    protected final Map<Direction, List<Position>> getMovablePositionsByDirections(final Position position,
                                                                                   final List<Direction> directions) {
        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    private Map<Direction, List<Position>> initMovablePositions(final List<Direction> directions) {
        final Map<Direction, List<Position>> movablePositions = new HashMap<>();
        for (final Direction direction : directions) {
            movablePositions.put(direction, new ArrayList<>());
        }
        return movablePositions;
    }

    private void putMovablePositionsByDirection(final Map<Direction, List<Position>> movablePositions,
                                                final Position position,
                                                final Direction direction) {
        final Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        final List<Position> positionsToDirection = movablePositions.get(direction);
        positionsToDirection.add(nextPosition);
    }

    @Override
    public Direction getPawnDirection() {
        throw new IllegalStateException("폰만 방향을 얻을 수 있습니다.");
    }
}
