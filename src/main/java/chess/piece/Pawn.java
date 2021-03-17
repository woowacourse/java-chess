package chess.piece;

import chess.Point;

public class Pawn extends Piece{
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    protected boolean isMovable(Point target) {
        return false;
    }
}
