package chess.piece;

import chess.Point;

public class Queen extends Piece {
    public Queen(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovable(Point target) {
        return false;
    }
}
