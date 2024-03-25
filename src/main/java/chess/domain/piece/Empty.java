package chess.domain.piece;

import chess.domain.point.Point;

public class Empty extends Piece {

    private static final Empty EMPTY = new Empty(Team.EMPTY);

    private Empty(Team team) {
        super(team);
    }

    static Empty getEmpty() {
        return EMPTY;
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return false;
    }
}
