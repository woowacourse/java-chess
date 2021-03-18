package chess.domain.piece;

import chess.domain.Point;

public class Pawn extends Piece {
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovableRoute(Piece target) {
        int distance = this.point.calculateDistance(target.point);
        if (distance == 1 && target instanceof Empty) {
            return true;
        }
        if (distance == 2 && !(target instanceof Empty)) {
            return true;
        }
        throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    protected Point moveOneStep(Point target) {
        return target;
    }
}
