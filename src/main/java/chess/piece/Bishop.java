package chess.piece;

import chess.Point;

public class Bishop extends Piece {
    public Bishop(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovable(Point target) {
        return false;
    }
}
