package chess.domain.move;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Move {

    private final List<Direction> directions;

    public Move(List<Direction> directions) {
        validate(directions);
        this.directions = directions.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public static Move of(Position source, Position target) {
        int deltaFile = target.getFileIndex() - source.getFileIndex();
        int deltaRank = target.getRankIndex() - source.getRankIndex();
        return new Move(Direction.from(deltaFile, deltaRank));
    }

    private void validate(List<Direction> directions) {
        validateNotEmpty(directions);
        validateNotBidirectional(directions);
    }

    private void validateNotBidirectional(List<Direction> directions) {
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

    private void validateNotEmpty(List<Direction> directions) {
        if (directions.isEmpty()) {
            throw new IllegalArgumentException("방향이 존재해야합니다.");
        }
    }

    public Move flipHorizontal() {
        return flip(Direction::flipHorizontal);
    }

    public Move flipVertical() {
        return flip(Direction::flipVertical);
    }

    private Move flip(Function<Direction, Direction> directionFlipper) {
        List<Direction> directions = this.directions.stream()
                .map(directionFlipper)
                .collect(Collectors.toList());
        return new Move(directions);
    }

    public Position findDestinationFrom(Position position) {
        Position destination = position;
        for (Direction direction : directions) {
            destination = destination.move(direction);
        }
        return destination;
    }

    public Move getUnitMove() {
        List<Direction> horizontalDirections = getHorizontalDirections();
        List<Direction> verticalDirections = getVerticalDirections();
        long gcd = getGCD(horizontalDirections.size(), verticalDirections.size());
        long unitHorizontalCount = horizontalDirections.size() / gcd;
        long unitVerticalCount = verticalDirections.size() / gcd;
        List<Direction> unitDirections = new ArrayList<>();
        unitDirections.addAll(repeatFirst(horizontalDirections, unitHorizontalCount));
        unitDirections.addAll(repeatFirst(verticalDirections, unitVerticalCount));
        return new Move(unitDirections);
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
        if (num1 == 0 || num2 == 0) {
            return Math.max(num1, num2);
        }
        if (num1 < num2) {
            return getGCD(num2, num1);
        }
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }

    private List<Direction> repeatFirst(List<Direction> directions, long count) {
        if (directions.size() == 0) {
            return Collections.emptyList();
        }
        Direction direction = directions.get(0);
        List<Direction> result = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            result.add(direction);
        }
        return result;
    }

    public Move repeat(int times) {
        List<Direction> timedDirections = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            timedDirections.addAll(directions);
        }
        return new Move(timedDirections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Move move = (Move) o;

        return Objects.equals(directions, move.directions);
    }

    @Override
    public int hashCode() {
        return directions != null ? directions.hashCode() : 0;
    }
}
