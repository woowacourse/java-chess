package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    public final static List<Direction> DIRECTIONS = List.of(
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static List<String> BLACK_INIT_LOCATIONS = List.of("c8", "f8");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("c1", "f1");

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
}
