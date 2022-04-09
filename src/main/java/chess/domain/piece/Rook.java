package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public final class Rook extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("a8"), Position.of("h8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("a1"), Position.of("h1"));

    private static final int ROOK_POINT = 5;

    protected Rook(final Color color) {
        super(color, Symbol.ROOK);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.rookDirections());
    }

    @Override
    public double getPoint() {
        return ROOK_POINT;
    }
}
