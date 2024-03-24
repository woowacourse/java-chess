package chess.util;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class RouteCalculator {

    private RouteCalculator() {
    }

    public static Set<Position> getVerticalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 1; i < movement.getRankDistance(); i++) {
            position = position.move(0, 1);
            positions.add(position);
        }

        return positions;
    }

    public static Set<Position> getHorizontalMiddlePositions(final Movement movement) {
        Position position = movement.getLefterPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 1; i < movement.getFileDistance(); i++) {
            position = position.move(1, 0);
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getRightDiagonalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 1; i < movement.getRankDistance(); i++) {
            position = position.move(1, 1);
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getLeftDiagonalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 1; i < movement.getRankDistance(); i++) {
            position = position.move(-1, 1);
            positions.add(position);
        }
        return positions;
    }
}
