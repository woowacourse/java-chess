package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class King extends Piece {
    public King(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        int distance = this.point.calculateDistance(target.point);

        if (distance == 1 || distance == 2) {
            return Optional.of(direction);
        }
        throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }
}
