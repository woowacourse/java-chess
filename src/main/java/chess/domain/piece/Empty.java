package chess.domain.piece;

import chess.domain.Point;

public class Empty extends Piece {
    public Empty(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Direction direction(Piece target) {
        return null;
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }
}
