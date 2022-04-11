package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public final class King extends Piece {

    public static final Position BLACK_INIT_LOCATION = Position.of("e8");
    public static final Position WHITE_INIT_LOCATION = Position.of("e1");

    private static final int KING_POINT = 0;

    protected King(final Color color) {
        super(color, Symbol.KING);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.kingDirections()) && checkPosition(from, to);
    }

    private boolean checkPosition(final Position from, final Position to) {
        final Direction direction = Direction.getDirection(from, to);
        final Position position = from.toDirection(direction);

        return position == to;
    }

    @Override
    public double getPoint() {
        return KING_POINT;
    }
}
