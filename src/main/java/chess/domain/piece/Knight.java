package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Knight extends FixedMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("b8"), Position.of("g8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("b1"), Position.of("g1"));

    private static final double KNIGHT_POINT = 2.5;

    public Knight(final Color color) {
        super(color, Symbol.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        List<Direction> directions = Direction.knightDirections();

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    @Override
    public double getPoint() {
        return KNIGHT_POINT;
    }
}
