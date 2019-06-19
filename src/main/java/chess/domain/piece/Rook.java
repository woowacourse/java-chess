package chess.domain.piece;

import chess.domain.board.Point;

public class Rook implements Piece{
    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 0) {
            return true;
        }
        if (gradient == Double.MAX_VALUE) {
            return true;
        }
        return false;
    }
}
