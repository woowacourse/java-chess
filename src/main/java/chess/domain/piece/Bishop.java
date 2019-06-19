package chess.domain.piece;

import chess.domain.board.Point;

public class Bishop implements Piece {
    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 1) {
            return true;
        }
        return false;
    }
}
