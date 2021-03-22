package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

final public class Position implements Comparable<Position> {
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
        return Arrays.asList(this.horizontal.getValue() - source.horizontal.getValue(),
                this.vertical.getValue() - source.vertical.getValue());
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

    public Direction decideDirection(Position target) {
        if (hasMiddlePath(target)) {
            return Direction.getMatchingDirection(getDirectionMatcher(target));
        }
        throw new IllegalArgumentException("유효하지 않은 방향입니다.");
    }

    private List<Integer> getDirectionMatcher(Position target) {
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
        return new Position(horizontal.getValue() + direction.getHorizontalDegree(),
                vertical.getValue() + direction.getVerticalDegree());
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
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
            return horizontal.getValue() - position.horizontal.getValue();
        }
        return position.vertical.getValue() - vertical.getValue();
    }
}
