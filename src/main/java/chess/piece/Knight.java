package chess.piece;

import chess.Point;

public class Knight extends Piece {
    public Knight(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovable(Point target) {
        return false;
    }
}
