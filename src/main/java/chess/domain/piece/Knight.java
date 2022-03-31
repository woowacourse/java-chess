package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Knight extends FixedMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("b8"), Position.of("g8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("b1"), Position.of("g1"));

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.SOUTH_SOUTH_EAST, Direction.SOUTH_SOUTH_WEST, Direction.NORTH_NORTH_EAST, Direction.NORTH_NORTH_WEST,
            Direction.EAST_EAST_SOUTH, Direction.EAST_EAST_NORTH, Direction.WEST_WEST_SOUTH, Direction.WEST_WEST_NORTH);
    private static final double KNIGHT_POINT = 2.5;

    public Knight(Color color) {
        super(color, PieceName.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return KNIGHT_POINT;
    }
}
