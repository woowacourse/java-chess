package chess.domain.piece;

import chess.domain.Point;

public class Pawn extends Piece {
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovableRoute(Point target) {
        return false;
    }
}
