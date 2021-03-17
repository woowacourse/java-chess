package chess.domain.piece;

import chess.domain.Point;

public class King extends Piece {
    public King(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovable(Point target) {
        return false;
    }
}
