package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {

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
        return Arrays.asList(this.horizontal.subtract(source.horizontal), this.vertical.subtract(source.vertical));
    }

    public boolean hasMiddlePath(final Position target) {
        return isLinear(target) || isDiagonal(target);
    }

    private boolean isLinear(final Position target) {
        final List<Integer> result = target.subtract(this);
        return result.stream()
            .filter(difference -> difference == 0)
            .count() == 1;
    }

    private boolean isDiagonal(final Position target) {
        final List<Integer> result = target.subtract(this);
        return Math.abs(result.get(0)) == Math.abs(result.get(1)) && !result.contains(0);
    }

    public Direction decideDirection(final Position target) {
        if (hasMiddlePath(target)) {
            final List<Integer> result = directionMatcher(target);
            return Direction.matchedDirection(result.get(0), result.get(1));
        }
        throw new IllegalArgumentException("유효하지 않은 방향입니다.");
    }

    private List<Integer> directionMatcher(final Position target) {
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
        return new Position(horizontal.increaseHorizontal(direction.horizontalDegree()),
            vertical.increaseVertical(direction.verticalDegree()));
    }

    public Horizontal horizontal() {
        return horizontal;
    }

    public Vertical vertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
            return horizontal.value() - position.horizontal.value();
        }
        return position.vertical.value() - vertical.value();
    }
}
