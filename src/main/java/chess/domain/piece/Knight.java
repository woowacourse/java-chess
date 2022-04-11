package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public final class Knight extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("b8"), Position.of("g8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("b1"), Position.of("g1"));

    private static final double KNIGHT_POINT = 2.5;

    protected Knight(final Color color) {
        super(color, Symbol.KNIGHT);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.knightDirections()) && checkPosition(from, to);
    }

    private boolean checkPosition(final Position from, final Position to) {
        final Direction direction = Direction.getDirection(from, to);
        final Position position = from.toDirection(direction);

        return position == to;
    }

    @Override
    public double getPoint() {
        return KNIGHT_POINT;
    }
}
