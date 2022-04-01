package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {

    private static final int PAWN_POINT = 1;

    protected Pawn(final Color color) {
        super(color, Symbol.PAWN);
    }

    @Override
    public abstract Map<Direction, List<Position>> getMovablePositions(final Position position);

    protected final Map<Direction, List<Position>> getMovablePositionsByDirection(final Position position,
                                                                                  final Direction direction) {
        final Map<Direction, List<Position>> movablePositions = initMovablePositions(direction);
        putFirstMovablePositionByDirection(movablePositions, position, direction);
        return movablePositions;
    }

    private Map<Direction, List<Position>> initMovablePositions(final Direction direction) {
        final Map<Direction, List<Position>> movablePositions = new HashMap<>();
        movablePositions.put(direction, new ArrayList<>());
        return movablePositions;
    }

    private void putFirstMovablePositionByDirection(final Map<Direction, List<Position>> movablePositions,
                                                    final Position position,
                                                    final Direction direction) {
        putMovablePositionByDirection(movablePositions, position, direction);
        putMovablePositionByDirection(movablePositions, position.toDirection(direction), direction);
    }

    private void putMovablePositionByDirection(final Map<Direction, List<Position>> movablePositions,
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
    public final double getPoint() {
        return PAWN_POINT;
    }
}
