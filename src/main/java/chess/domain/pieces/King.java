package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        Direction currentDirection = calculateDirection(source, target);
        return makePath(source, target, currentDirection);
    }

    private Direction calculateDirection(Point source, Point target) {
        List<Direction> kingDirections = Direction.everyDirection();
        Direction direction = Direction.of(source, target);

        if (!kingDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }
}
