package chess.util;

import chess.domain.Movement;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class RouteCalculator {

    private RouteCalculator() {
    }

    public static Set<Position> getVerticalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < movement.getRankDistance() - 1; i++) {
            position = position.up();
            positions.add(position);
        }

        return positions;
    }

    public static Set<Position> getHorizontalMiddlePositions(final Movement movement) {
        Position position = movement.getLefterPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < movement.getFileDistance() - 1; i++) {
            position = position.right();
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getRightDiagonalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < movement.getRankDistance() - 1; i++) {
            position = position.upRight();
            positions.add(position);
        }
        return positions;
    }

    public static Set<Position> getLeftDiagonalMiddlePositions(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < movement.getRankDistance() - 1; i++) {
            position = position.upLeft();
            positions.add(position);
        }
        return positions;
    }
}
