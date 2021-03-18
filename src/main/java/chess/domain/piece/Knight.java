package chess.domain.piece;

import chess.domain.Point;

public class Knight extends Piece {
    public Knight(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovableRoute(Piece target) {
        return false;
    }

    @Override
    protected Point moveOneStep(Point target) {
        return null;
    }
}
