package chess.domain.piece;

import chess.domain.Point;

public class Queen extends Piece {
    public Queen(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Direction direction(Piece target) {
        return null;
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return null;
    }
}
