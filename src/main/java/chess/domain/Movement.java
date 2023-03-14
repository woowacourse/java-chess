package chess.domain;

import java.util.List;
import java.util.function.Predicate;

public class Movement {

    private final List<Direction> directions;

    public Movement(List<Direction> directions) {
        validate(directions);
        this.directions = directions;
    }

    private void validate(List<Direction> directions) {
        long horizontalSize = getSize(directions, Direction::isHorizontal);
        long verticalSize = getSize(directions, Direction::isVertical);
        if (horizontalSize == 2 || verticalSize == 2) {
            throw new IllegalArgumentException("양방향이 존재하면 안됩니다");
        }
    }

    private long getSize(List<Direction> directions, Predicate<Direction> directionPredicate) {
        return directions.stream()
            .filter(directionPredicate)
            .distinct()
            .count();
    }

    public boolean isSameAngle(Movement movement) {
        int dx1 = 0;
        int dy1 = 0;
        for (Direction direction : directions) {
            dx1 += direction.getDx();
            dy1 += direction.getDy();
        }
        int dx2 = 0;
        int dy2 = 0;
        for (Direction direction : movement.directions) {
            dx2 += direction.getDx();
            dy2 += direction.getDy();
        }
        return dx1 * dy2 == dx2 * dy1;
    }

    public boolean isSameWith(Movement movement) {
        if (directions.size() != movement.directions.size()) {
            return false;
        }
        return directions.containsAll(movement.directions);
    }
}
