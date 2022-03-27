package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Queen extends StraightMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("d8");
    public static final Position WHITE_INIT_LOCATION = Position.of("d1");

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public Queen(Color color) {
        super(color, PieceName.QUEEN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return 9;
    }
}
