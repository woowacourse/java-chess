package chess.domain.piece;

import chess.domain.Point;

public final class Knight extends Piece {

    private static final String name = "N";

    Knight(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return Math.abs(currentPoint.multiplyAxis(nextPoint)) == 2;
    }
}
