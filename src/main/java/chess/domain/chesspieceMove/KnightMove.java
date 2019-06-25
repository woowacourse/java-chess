package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class KnightMove implements Move {
    private static final int MAX_DISTANCE = 2;
    private static final int MIN_DISTANCE = 1;
    private static KnightMove knightMove;

    private KnightMove() {
    }

    public static KnightMove getInstance() {
        if (Objects.isNull(knightMove))
            return new KnightMove();

        return knightMove;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }
        return Collections.singletonList(target);
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && (isColumnRoute(source, target)) || isRowRoute(source, target);
    }

    private boolean isRowRoute(Position source, Position target) {
        return (Math.abs(source.calculateColumnDistance(target)) == MIN_DISTANCE)
                && (Math.abs(source.calculateRowDistance(target)) == MAX_DISTANCE);
    }

    private boolean isColumnRoute(Position source, Position target) {
        return (Math.abs(source.calculateColumnDistance(target)) == MIN_DISTANCE)
                && (Math.abs(source.calculateRowDistance(target)) == MIN_DISTANCE);
    }
}
