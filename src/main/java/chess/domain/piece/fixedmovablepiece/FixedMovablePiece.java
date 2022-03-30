package chess.domain.piece.fixedmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FixedMovablePiece extends Piece {

    protected FixedMovablePiece(Color color, PieceName name) {
        super(color, name);
    }

    @Override
    public abstract Map<Direction, List<Position>> getMovablePositions(Position position);

    protected final Map<Direction, List<Position>> getMovablePositionsByDirections(Position position,
                                                                                   List<Direction> directions) {
        Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    private Map<Direction, List<Position>> initMovablePositions(List<Direction> directions) {
        Map<Direction, List<Position>> movablePositions = new HashMap<>();
        for (Direction direction : directions) {
            movablePositions.put(direction, new ArrayList<>());
        }
        return movablePositions;
    }

    private void putMovablePositionsByDirection(Map<Direction, List<Position>> movablePositions, Position position,
                                                Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        List<Position> positionsToDirection = movablePositions.get(direction);
        positionsToDirection.add(nextPosition);
    }
}
