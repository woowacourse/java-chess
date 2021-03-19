package chess.domain.piece;

import chess.domain.Point;

public class Bishop extends Piece {
    public Bishop(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Direction direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        System.out.println(direction);
        if (!direction.equals(Direction.NORTH_WEST) && !direction.equals(Direction.NORTH_EAST)
                && !direction.equals(Direction.SOUTH_EAST) && !direction.equals(Direction.SOUTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        return direction;
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }
}
