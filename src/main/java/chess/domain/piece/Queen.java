package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Queen extends StraightMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("d8");
    public static final Position WHITE_INIT_LOCATION = Position.of("d1");

    private static final int QUEEN_POINT = 9;

    public Queen(final Color color) {
        super(color, Symbol.QUEEN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        final List<Direction> directions = Direction.queenDirections();

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    @Override
    public double getPoint() {
        return QUEEN_POINT;
    }
}
