package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class King extends FixedMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("e8");
    public static final Position WHITE_INIT_LOCATION = Position.of("e1");

    private static final int KING_POINT = 0;

    public King(final Color color) {
        super(color, Symbol.KING);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        List<Direction> directions = Direction.kingDirections();

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    @Override
    public double getPoint() {
        return KING_POINT;
    }
}
