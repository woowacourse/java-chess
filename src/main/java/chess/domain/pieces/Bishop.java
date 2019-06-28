package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        Direction currentDirection = calculateDirection(source, target);
        return makePath(source, target, currentDirection);
    }

    private Direction calculateDirection(Point source, Point target) throws IllegalArgumentException {
        List<Direction> bishopDirections = Direction.diagonalDirection();
        Direction direction = Direction.of(source, target);

        if (!bishopDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }
}
