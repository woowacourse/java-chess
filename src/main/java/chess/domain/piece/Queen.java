package chess.domain.piece;

import chess.domain.Point;

public class Queen extends Piece {
    public Queen(String name, String color, Point point) {
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
