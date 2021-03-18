package chess.domain.piece;

import chess.domain.Point;

public class Bishop extends Piece {
    public Bishop(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public boolean isMovableRoute(Piece target) {
        return false;
    }

    @Override
    public Point moveOneStep(Point target) {
        return null;
    }
}
