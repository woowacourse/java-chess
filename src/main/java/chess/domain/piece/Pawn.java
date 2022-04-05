package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("a7"), Position.of("b7"), Position.of("c7"), Position.of("d7"),
            Position.of("e7"), Position.of("f7"), Position.of("g7"), Position.of("h7"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("a2"), Position.of("b2"), Position.of("c2"), Position.of("d2"),
            Position.of("e2"), Position.of("f2"), Position.of("g2"), Position.of("h2"));

    private static final int PAWN_POINT = 1;

    public Pawn(final Color color) {
        super(color, Symbol.PAWN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        Direction direction = Direction.pawnDirection(color);

        final Map<Direction, List<Position>> movablePositions = initMovablePositions(direction);
        putMovablePositionByDirection(movablePositions, position, direction);
        return movablePositions;
    }

    private Map<Direction, List<Position>> initMovablePositions(final Direction direction) {
        final Map<Direction, List<Position>> movablePositions = new HashMap<>();
        movablePositions.put(direction, new ArrayList<>());
        return movablePositions;
    }

    private void putMovablePositionByDirection(final Map<Direction, List<Position>> movablePositions,
                                               final Position position,
                                               final Direction direction) {
        final Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        final List<Position> positionsToDirection = movablePositions.get(direction);
        positionsToDirection.add(nextPosition);
        if (isFirstMove(position)) {
            putMovablePositionByDirection(movablePositions, nextPosition, direction);
        }
    }

    private boolean isFirstMove(final Position position) {
        return isBlackMovingFirst(position) || isWhiteMovingFirst(position);
    }

    private boolean isBlackMovingFirst(final Position position) {
        return color == Color.BLACK && BLACK_INIT_LOCATIONS.contains(position);
    }

    private boolean isWhiteMovingFirst(final Position position) {
        return color == Color.WHITE && WHITE_INIT_LOCATIONS.contains(position);
    }

    @Override
    public final double getPoint() {
        return PAWN_POINT;
    }
}
