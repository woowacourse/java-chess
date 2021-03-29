package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position implements Comparable<Position> {
    private final Horizontal horizontal;
    private final Vertical vertical;

    public Position(final Horizontal horizontal, final Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position(final String horizontal, final String vertical) {
        this(Horizontal.of(horizontal), Vertical.of(vertical));
    }

    public Position(final int horizontal, final int vertical) {
        this(Horizontal.of(horizontal), Vertical.of(vertical));
    }

    public List<Integer> subtract(final Position source) {
        return Arrays.asList(this.horizontal.subtractedValue(source.horizontal),
                this.vertical.subtractedValue(source.vertical));
    }

    public boolean hasMiddlePath(final Position target) {
        final List<Integer> result = target.subtract(this);
        return linear(result) || diagonal(result);
    }

    private boolean linear(final List<Integer> result) {
        return result.stream()
                .filter(difference -> difference == 0)
                .count() == 1;
    }

    private boolean diagonal(final List<Integer> result) {
        return Math.abs(result.get(0)) == Math.abs(result.get(1)) && !result.contains(0);
    }

    public Direction directionToTarget(Position target) {
        if (hasMiddlePath(target)) {
            List<Integer> directionFormat = directionFormat(target);
            return Direction.matchingDirection(
                    directionFormat.get(0), directionFormat.get(1));
        }
        throw new IllegalArgumentException("유효하지 않은 방향입니다.");
    }

    private List<Integer> directionFormat(Position target) {
        final List<Integer> result = target.subtract(this);
        final int abs = result.stream()
                .filter(difference -> difference != 0)
                .map(Math::abs)
                .findFirst()
                .orElse(0);

        return result.stream()
                .map(difference -> difference / abs)
                .collect(Collectors.toList());
    }

    public Position next(final Direction direction) {
        return new Position(horizontal.addedValue(direction.horizontalDegree()),
                vertical.addedValue(direction.verticalDegree()));
    }

    public Horizontal horizontal() {
        return horizontal;
    }

    public Vertical vertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public int compareTo(Position position) {
        if (vertical.isSameValue(position.vertical)) {
            return horizontal.subtractedValue(position.horizontal);
        }
        return position.vertical.subtractedValue(vertical);
    }
}
