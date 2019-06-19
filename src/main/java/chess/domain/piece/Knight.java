package chess.domain.piece;

import chess.domain.board.Point;

public class Knight implements Piece {

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 0) {
            return false;
        }
        if (gradient == Double.MAX_VALUE) {
            return false;
        }
        int distanceSum = Math.abs(next.xDistance(prev)) + Math.abs(next.yDistance(prev));
        if (distanceSum == 3) {
            return true;
        }
        return false;
    }
}
