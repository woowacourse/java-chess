package chess.domain.piece;

import chess.domain.Point;

public class Knight extends Piece {
    public Knight(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovableRoute(Point target) {
        return false;
    }
}
