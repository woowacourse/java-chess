package chess.util;

import chess.domain.Direction;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class RouteCalculator {

    private RouteCalculator() {
    }

    public static Set<Position> getVerticalMiddlePositions(final Position source, final Position target) {
        Position position = getLowerPosition(source, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < source.getRankDistance(target) - 1; i++) {
            position = position.up();
            positions.add(position);
        }

        return positions;
    }

    private static Position getLowerPosition(final Position source, final Position target) {
        if (Direction.of(source, target).contains(Direction.DOWN)) {
            return source;
        }

        return target;
    }

    public static Set<Position> getHorizontalMiddlePositions(final Position source, final Position target) {
        Position position = getLefterPosition(source, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < source.getFileDistance(target) - 1; i++) {
            position = position.right();
            positions.add(position);
        }
        return positions;
    }

    private static Position getLefterPosition(final Position source, final Position target) {
        if (Direction.of(source, target).contains(Direction.LEFT)) {
            return source;
        }

        return target;
    }

    public static Set<Position> getRightDiagonalMiddlePositions(final Position source, final Position target) {
        Position position = getLowerPosition(source, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < source.getRankDistance(target) - 1; i++) {
            position = position.rightUp();
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getLeftDiagonalMiddlePositions(final Position source, final Position target) {
        Position position = getLowerPosition(source, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < source.getRankDistance(target) - 1; i++) {
            position = position.leftUp();
            positions.add(position);
        }
        return positions;
    }
}
