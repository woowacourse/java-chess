package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public final class Queen extends Piece {

    public static final Position BLACK_INIT_LOCATION = Position.of("d8");
    public static final Position WHITE_INIT_LOCATION = Position.of("d1");

    private static final int QUEEN_POINT = 9;

    protected Queen(final Color color) {
        super(color, Symbol.QUEEN);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.queenDirections());
    }

    @Override
    public double getPoint() {
        return QUEEN_POINT;
    }
}
