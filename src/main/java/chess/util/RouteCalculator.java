package chess.util;

import chess.domain.Direction;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class RouteCalculator {

    private RouteCalculator() {
    }

    public static Set<Position> getVerticalMiddlePositions(final Position current, final Position target) {
        Position position = getLowerPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getRankDistance(target) - 1; i++) {
            position = position.up();
            positions.add(position);
        }

        return positions;
    }

    private static Position getLowerPosition(final Position current, final Position target) {
        if (Direction.of(current, target).contains(Direction.DOWN)) {
            return current;
        }

        return target;
    }

    public static Set<Position> getHorizontalMiddlePositions(final Position current, final Position target) {
        Position position = getLefterPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getFileDistance(target) - 1; i++) {
            position = position.right();
            positions.add(position);
        }
        return positions;
    }

    private static Position getLefterPosition(final Position current, final Position target) {
        if (Direction.of(current, target).contains(Direction.LEFT)) {
            return current;
        }

        return target;
    }

    public static Set<Position> getRightDiagonalMiddlePositions(final Position current, final Position target) {
        Position position = getLowerPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getRankDistance(target) - 1; i++) {
            position = position.upRight();
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getLeftDiagonalMiddlePositions(final Position current, final Position target) {
        Position position = getLowerPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getRankDistance(target) - 1; i++) {
            position = position.upLeft();
            positions.add(position);
        }
        return positions;
    }
}
