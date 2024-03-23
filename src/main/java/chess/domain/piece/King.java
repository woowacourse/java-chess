package chess.domain.piece;

import chess.domain.Point;

public final class King extends Piece {

    private static final String name = "K";

    King(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return currentPoint.isAround(nextPoint);
    }
}
