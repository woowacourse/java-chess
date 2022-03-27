package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Bishop extends StraightMovablePiece {

    private final static List<Direction> DIRECTIONS = List.of(
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("c8"), Position.of("f8"));
    public static List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("c1"), Position.of("f1"));

    public Bishop(Color color) {
        super(color, PieceName.BISHOP);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return 3;
    }
}
