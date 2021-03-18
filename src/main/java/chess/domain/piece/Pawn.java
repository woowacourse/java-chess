package chess.domain.piece;

import chess.domain.Point;

public class Pawn extends Piece {
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public boolean isMovableRoute(Piece target) {
        //TODO: 폰은 직진만 가능
        //TODO: 폰은 시작 때만 두 칸 이동 가능
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
    public Point moveOneStep(Point target) {
        return target;
    }
}
