package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Movement {

    private final List<Direction> directions;

    public Movement(List<Direction> directions) {
        validate(directions);
        this.directions = new ArrayList<>(directions);
    }

    public Movement() {
        this(Collections.emptyList());
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

    public Movement flipHorizontal() {
        return flip(Direction::flipHorizontal);
    }

    public Movement flipVertical() {
        return flip(Direction::flipVertical);
    }

    private Movement flip(Function<Direction, Direction> directionFlipper) {
        List<Direction> directions = this.directions.stream()
                .map(directionFlipper)
                .collect(Collectors.toUnmodifiableList());
        return new Movement(directions);
    }

    public Position findDestination(Position position) {
        Position destination = position;
        for (Direction direction : directions) {
            destination = destination.move(direction);
        }
        return destination;
    }

    public Movement getUnitMovement() {
        List<Direction> horizontalDirections = getHorizontalDirections();
        List<Direction> verticalDirections = getVerticalDirections();
        if (horizontalDirections.size() == 0 && verticalDirections.size() == 0) {
            return new Movement();
        }
        if (horizontalDirections.size() == 0) {
            return new Movement(List.of(verticalDirections.get(0)));
        }
        if (verticalDirections.size() == 0) {
            return new Movement(List.of(horizontalDirections.get(0)));
        }
        long horizontalCount = horizontalDirections.size();
        long verticalCount = verticalDirections.size();
        long gcd = getGCD(horizontalCount, verticalCount);
        horizontalCount /= gcd;
        verticalCount /= gcd;
        List<Direction> unitDirections = new ArrayList<>();
        unitDirections.addAll(repeat(horizontalDirections.get(0), horizontalCount));
        unitDirections.addAll(repeat(verticalDirections.get(0), verticalCount));
        return new Movement(unitDirections);
    }

    private List<Direction> repeat(Direction direction, long count) {
        List<Direction> directions = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            directions.add(direction);
        }
        return directions;
    }

    private List<Direction> getHorizontalDirections() {
        return directions.stream()
                .filter(Direction::isHorizontal)
                .collect(Collectors.toList());
    }

    private List<Direction> getVerticalDirections() {
        return directions.stream()
                .filter(Direction::isVertical)
                .collect(Collectors.toList());
    }

    private long getGCD(long num1, long num2) {
        if (num1 < num2) {
            return getGCD(num2, num1);
        }
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }
}
