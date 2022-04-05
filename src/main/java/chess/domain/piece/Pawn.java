package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

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
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.pawnDirection(color)) && checkPosition(from, to);
    }

    private boolean checkPosition(final Position from, final Position to) {
        final Direction direction = Direction.getDirection(from, to);
        final Position position = from.toDirection(direction);
        final List<Direction> directions = Direction.pawnStraightDirection();

        if (isFirstMove(from) && directions.contains(direction)) {
            Position additionalPosition = position.toDirection(direction);
            return position.equals(to) || additionalPosition.equals(to);
        }

        return position.equals(to);
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
