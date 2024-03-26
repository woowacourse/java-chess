package chess.domain.piece;

import chess.domain.point.Point;

import java.util.Map;

public class Empty extends Piece {
    public static final Empty INSTANCE = new Empty();

    private Empty() {
        super(Type.EMPTY, Team.EMPTY);
    }

    @Override
    public boolean isMovable(final Point departure, final Point destination, final Map<Point, Piece> board) {
        return false;
    }
}
