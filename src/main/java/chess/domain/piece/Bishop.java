package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class Bishop extends Piece {
    public Bishop(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        if (!direction.equals(Direction.NORTH_WEST) && !direction.equals(Direction.NORTH_EAST)
                && !direction.equals(Direction.SOUTH_EAST) && !direction.equals(Direction.SOUTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        return Optional.of(direction);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }
}
