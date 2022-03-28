package chess.domain.piece.straightmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class StraightMovablePiece extends Piece {

    protected StraightMovablePiece(Color color, PieceName name) {
        super(color, name);
    }

    @Override
    public abstract Map<Direction, List<Position>> getMovablePositions(Position position);

    protected final Map<Direction, List<Position>> getMovablePositionsByDirections(Position position, List<Direction> directions) {
        Map<Direction, List<Position>> movable = new HashMap<>();
        for (Direction direction : directions) {
            movable.put(direction, new ArrayList<>());
            putMovablePositionsByDirection(movable, position, direction);
        }
        return movable;
    }

    private final void putMovablePositionsByDirection(Map<Direction, List<Position>> movable, Position position,
                                                Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        movable.get(direction).add(nextPosition);
        putMovablePositionsByDirection(movable, nextPosition, direction);
    }
}
