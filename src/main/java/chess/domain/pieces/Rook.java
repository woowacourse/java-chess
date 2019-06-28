package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        Direction currentDirection = calculateDirection(source, target);
        return makePath(source, target, currentDirection);
    }

    private Direction calculateDirection(Point source, Point target) {
        List<Direction> rookDirections = Direction.linearDirection();
        Direction direction = Direction.of(source, target);

        if (!rookDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }
}
