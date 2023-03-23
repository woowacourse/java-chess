package chess.domain.move;

import static chess.domain.move.Direction.HORIZONTALS;
import static chess.domain.move.Direction.VERTICALS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.position.Position;
import chess.util.Mathematics;

public class Directions {

    private final List<Direction> directions;

    public Directions(List<Direction> directions) {
        validateSingleDirection(directions);
        this.directions = directions.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Directions of(Direction... directions) {
        return new Directions(List.of(directions));
    }

    private void validateSingleDirection(List<Direction> directions) {
        Set<Direction> distinctDirections = new HashSet<>(directions);
        if (distinctDirections.containsAll(VERTICALS) || distinctDirections.containsAll(HORIZONTALS)) {
            throw new IllegalArgumentException("수직이나 수평으로 양방향이면 안됩니다");
        }
    }

    public Directions splitIntoMinimumUnit() {
        return new Directions(directions.stream()
                .distinct()
                .flatMap(this::getMinimumUnitOf)
                .collect(Collectors.toList()));
    }

    private Stream<Direction> getMinimumUnitOf(Direction direction) {
        long gcd = Mathematics.getGCD(count(Direction::isHorizontal), count(Direction::isVertical));
        long minimumUnitCount = count(it -> it.equals(direction)) / gcd;
        return direction.repeat(minimumUnitCount).stream();
    }

    private long count(Predicate<Direction> condition) {
        return directions.stream()
                .filter(condition)
                .count();
    }

    public Position move(Position position) {
        Position destination = position;
        for (Direction direction : directions) {
            destination = destination.move(direction);
        }
        return destination;
    }

    public Directions join(Directions other) {
        List<Direction> directions = new ArrayList<>(this.directions);
        directions.addAll(other.directions);
        return new Directions(directions);
    }

    public boolean isEmpty() {
        return directions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Directions that = (Directions)o;

        return Objects.equals(directions, that.directions);
    }

    @Override
    public int hashCode() {
        return directions != null ? directions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return directions.toString();
    }
}
