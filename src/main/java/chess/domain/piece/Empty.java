package chess.domain.piece;

import chess.domain.Point;

public class Empty extends Piece {

    private static final String NAME = ".";

    private static Empty empty;

    private Empty(Team team) {
        super(NAME, team);
    }

    public static Empty getEmpty() {
        if (empty == null) {
            empty = Empty.getEmpty();
        }
        return empty;
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return false;
    }
}
