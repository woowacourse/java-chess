package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Bishop extends StraightMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("c8"), Position.of("f8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("c1"), Position.of("f1"));

    private static final int BISHOP_POINT = 3;

    public Bishop(final Color color) {
        super(color, Symbol.BISHOP);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        final List<Direction> directions = Direction.bishopDirections();

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    @Override
    public double getPoint() {
        return BISHOP_POINT;
    }
}
