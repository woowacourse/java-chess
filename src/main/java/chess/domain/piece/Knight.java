package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class Knight extends Piece {
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        int distance = this.point.calculateDistance(target.point);

        if (distance != 5) {
            throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }
}
