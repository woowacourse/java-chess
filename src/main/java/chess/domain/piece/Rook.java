package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Rook extends StraightMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("a8"), Position.of("h8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("a1"), Position.of("h1"));

    private static final int ROOK_POINT = 5;

    public Rook(final Color color) {
        super(color, Symbol.ROOK);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        final List<Direction> directions = Direction.rookDirections();

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(directions);
        for (Direction direction : directions) {
            putMovablePositionsByDirection(movablePositions, position, direction);
        }
        return movablePositions;
    }

    @Override
    public double getPoint() {
        return ROOK_POINT;
    }
}
