package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {

    public final static List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);

    public final static List<String> BLACK_INIT_LOCATIONS = List.of("a8", "h8");
    public final static List<String> WHITE_INIT_LOCATIONS = List.of("a1", "h1");

    public Rook(Color color) {
        super(color, PieceName.ROOK);
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
