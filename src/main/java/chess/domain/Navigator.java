package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Navigator {
    private static final int ROUNDING_DIGIT = 100;
    private static final int FIRST_INDEX = 0;

    private final Point start;
    private final Point end;

    public Navigator(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Direction getDirection(List<Direction> directions) {
        List<Direction> filteredDirection = directions.stream()
                .filter(this::isParallel)
                .collect(Collectors.toList());
        return filteredDirection.isEmpty() ? Direction.NOT_FIND : filteredDirection.get(FIRST_INDEX);
    }

    private boolean isParallel(Direction direction) {
        Point vector = start.makeVector(end);
        double dotProduction = (double) vector.dotProduct(direction.getPosition());
        double vectorScalar = vector.calScalar();
        double directionScalar = direction.getPosition().calScalar();
        double lengthProduction = (Math.floor((vectorScalar * directionScalar * ROUNDING_DIGIT))) / ROUNDING_DIGIT;
        return dotProduction == lengthProduction;
    }
}
