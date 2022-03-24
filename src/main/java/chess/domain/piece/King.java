package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    public final static List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static String BLACK_INIT_LOCATION = "e8";
    public static String WHITE_INIT_LOCATION = "e1";

    public King(Color color) {
        super(color, PieceName.KING);
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
    }
}
