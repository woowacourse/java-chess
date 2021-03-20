package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class Rook extends Piece {
    private static final int ROOK_SCORE = 5;

    public Rook(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        if (!direction.equals(Direction.NORTH) && !direction.equals(Direction.WEST)
                && !direction.equals(Direction.EAST) && !direction.equals(Direction.SOUTH)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        return Optional.of(direction);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
