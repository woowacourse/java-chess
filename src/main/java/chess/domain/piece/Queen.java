package chess.domain.piece;

import chess.domain.Point;

public final class Queen extends Piece {

    private static final String name = "Q";

    Queen(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return currentPoint.isStraight(nextPoint) || currentPoint.isDiagonal(nextPoint);
    }
}
