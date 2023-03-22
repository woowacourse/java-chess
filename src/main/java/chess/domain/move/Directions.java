package chess.domain.move;

import static chess.domain.move.Axis.HORIZON;
import static chess.domain.move.Axis.VERTICAL;
import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.domain.position.Position;
import chess.util.Mathematics;

public class Directions {

    public static final Directions NONE = new Directions(Collections.emptyList());

    private final List<Direction> directions;

    public Directions(List<Direction> directions) {
        validateVerticalBidirectional(directions);
        validateHorizontalBidirectional(directions);
        this.directions = directions.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Directions of(Direction... directions) {
        return new Directions(List.of(directions));
    }

    private void validateHorizontalBidirectional(List<Direction> directions) {
        if (directions.contains(UP) && directions.contains(DOWN)) {
            throw new IllegalArgumentException("수직이나 수평으로 양방향이면 안됩니다");
        }
    }

    private void validateVerticalBidirectional(List<Direction> directions) {
        if (directions.contains(LEFT) && directions.contains(RIGHT)) {
            throw new IllegalArgumentException("수직이나 수평으로 양방향이면 안됩니다");
        }
    }

    public long count(Direction direction) {
        return directions.stream()
                .filter(it -> it.equals(direction))
                .count();
    }

    public long countDirectionsIn(Axis axis) {
        return directions.stream()
                .filter(it -> it.isIn(axis))
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

    public Optional<Direction> getDirectionOf(Axis axis) {
        return directions.stream()
                .filter(direction -> direction.isIn(axis))
                .findFirst();
    }

    public boolean isEmpty() {
        return directions.isEmpty();
    }

    public long countMinimumUnits() {
        return Mathematics.getGCD(countDirectionsIn(HORIZON), countDirectionsIn(VERTICAL));
    }

    @Override
    public String toString() {
        return directions.toString();
    }
}
