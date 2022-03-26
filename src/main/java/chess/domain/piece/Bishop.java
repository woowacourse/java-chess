package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    private final static List<Direction> DIRECTIONS = List.of(
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static List<Position> BLACK_INIT_LOCATIONS = List.of(
            new Position("c8"), new Position("f8"));
    public static List<Position> WHITE_INIT_LOCATIONS = List.of(
            new Position("c1"), new Position("f1"));

    public Bishop(Color color) {
        super(color, PieceName.BISHOP);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        Map<Direction, List<Position>> movable = new HashMap<>();
        for (Direction direction : DIRECTIONS) {
            movable.put(direction, new ArrayList<>());
            putMovablePositionsByDirection(movable, position, direction);
        }
        return movable;
    }

    private void putMovablePositionsByDirection(Map<Direction, List<Position>> movable, Position position,
                                                Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        movable.get(direction).add(nextPosition);
        putMovablePositionsByDirection(movable, nextPosition, direction);
    }

    @Override
    public double getPoint() {
        return 3;
    }
}
