package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Bishop extends StraightMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("c8"), Position.of("f8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("c1"), Position.of("f1"));

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    private static final int BISHOP_POINT = 3;

    public Bishop(Color color) {
        super(color, PieceName.BISHOP);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return BISHOP_POINT;
    }
}
