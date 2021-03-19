package chess.domain.piece;

import chess.domain.Point;

public class Queen extends Piece {
    public Queen(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Direction direction(Piece target) {
        return Direction.findDirection(this.point, target.point);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

}
