package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public static Position from(final String position) {
       return getPositionByCommands(position.split(""));
    }

    private static Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    public List<Integer> subtract(final Position source) {
        return Arrays.asList(this.horizontal.subtract(source.horizontal), this.vertical.subtract(source.vertical));
    }

    public boolean hasMiddlePath(final Position target) {
        return isLinear(target) || isDiagonal(target);
    }

    private boolean isLinear(final Position target) {
        final List<Integer> result = target.subtract(this);
        final Difference difference = new Difference(result);
        return difference.hasZeroValue() && !difference.allZeroValue();
    }

    private boolean isDiagonal(final Position target) {
        final List<Integer> result = target.subtract(this);
        final Difference difference = new Difference(result);
        return difference.isSameAbsoluteValue() && !difference.hasZeroValue();
    }

    public Direction decideDirection(final Position target) {
        if (hasMiddlePath(target)) {
            final Difference difference = directionMatcher(target);
            return Direction.matchedDirection(difference.horizontalDegree(), difference.verticalDegree());
        }
        throw new IllegalArgumentException("유효하지 않은 방향입니다.");
    }

    private Difference directionMatcher(final Position target) {
        final List<Integer> result = target.subtract(this);
        final Difference difference = new Difference(result);
        return difference.makeUnitLength();
    }

    public Position next(final Direction direction) {
        return new Position(horizontal.increaseHorizontal(direction.horizontalDegree()),
            vertical.increaseVertical(direction.verticalDegree()));
    }

    public boolean isSameColumn(final int column) {
        return horizontal.isSameValue(column);
    }

    public Horizontal horizontal() {
        return horizontal;
    }

    public String horizontalSymbol() {
        return horizontal.symbol();
    }

    public Vertical vertical() {
        return vertical;
    }

    public String verticalSymbol() {
        return vertical.symbol();
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
