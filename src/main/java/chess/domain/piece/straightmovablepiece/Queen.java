package chess.domain.piece.straightmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.Symbol;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Queen extends StraightMovablePiece {

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static final Position BLACK_INIT_LOCATION = Position.of("d8");
    public static final Position WHITE_INIT_LOCATION = Position.of("d1");

    private static final int QUEEN_POINT = 9;

    public Queen(final Color color) {
        super(color, Symbol.QUEEN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return QUEEN_POINT;
    }
}
