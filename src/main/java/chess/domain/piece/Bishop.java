package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public final class Bishop extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("c8"), Position.of("f8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("c1"), Position.of("f1"));

    private static final int BISHOP_POINT = 3;

    protected Bishop(final Color color) {
        super(color, Symbol.BISHOP);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.bishopDirections());
    }

    @Override
    public double getPoint() {
        return BISHOP_POINT;
    }
}
