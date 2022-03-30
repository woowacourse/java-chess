package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {

    private static final int PAWN_POINT = 1;

    protected Pawn(Color color) {
        super(color, PieceName.PAWN);
    }

    @Override
    public abstract Map<Direction, List<Position>> getMovablePositions(Position position);

    protected final Map<Direction, List<Position>> getMovablePositionsByDirection(Position position,
                                                                                  Direction direction) {
        Map<Direction, List<Position>> movablePositions = initMovablePositions(direction);
        putFirstMovablePositionByDirection(movablePositions, position, direction);
        return movablePositions;
    }

    private Map<Direction, List<Position>> initMovablePositions(Direction direction) {
        Map<Direction, List<Position>> movablePositions = new HashMap<>();
        movablePositions.put(direction, new ArrayList<>());
        return movablePositions;
    }

    private void putFirstMovablePositionByDirection(Map<Direction, List<Position>> movablePositions, Position position,
                                                    Direction direction) {
        putMovablePositionByDirection(movablePositions, position, direction);
        putMovablePositionByDirection(movablePositions, position.toDirection(direction), direction);
    }

    private void putMovablePositionByDirection(Map<Direction, List<Position>> movable, Position position,
                                               Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        List<Position> positionsToDirection = movable.get(direction);
        positionsToDirection.add(nextPosition);
    }

    @Override
    public final double getPoint() {
        return PAWN_POINT;
    }
}
