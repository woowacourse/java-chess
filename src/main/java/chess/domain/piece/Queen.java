package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class Queen extends Piece {
    public Queen(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        return Optional.of(direction);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

}
