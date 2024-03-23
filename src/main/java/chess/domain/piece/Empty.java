package chess.domain.piece;

import chess.domain.Point;

public class Empty extends Piece {

    private static final String NAME = ".";

    private static final Empty EMPTY = new Empty(Team.EMPTY);

    private Empty(Team team) {
        super(NAME, team);
    }

    static Empty getEmpty() {
        return EMPTY;
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return false;
    }
}
