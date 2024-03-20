package chess.domain.piece;

import chess.domain.Point;

public final class Rook extends Piece {

    private static final String name = "R";

    public Rook(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return currentPoint.isStraight(nextPoint);
    }
}
