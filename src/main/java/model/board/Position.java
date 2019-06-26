package model.board;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {
    private static final Position[][] CACHE = IntStream.range(Coordinate.MIN, Coordinate.MAX)
                                                    .mapToObj(x ->
                                                            IntStream.range(Coordinate.MIN, Coordinate.MAX)
                                                                    .mapToObj(y -> new Position(x, y))
                                                                    .toArray(Position[]::new)
                                                    ).toArray(Position[][]::new);
    private static final Pattern validator = Pattern.compile("\\s*[a-zA-Z][0-9]\\s*");

    private final Coordinate x;
    private final Coordinate y;

    public static Position of(final String position) {
        return CACHE[position.substring(0, 1).toLowerCase().charAt(0) - 'a'][Integer.parseInt(position.substring(1)) - 1];
    }

    public static Optional<Position> ofSafe(final String input) {
        try {
            if (validator.matcher(input).matches()) {
                return Optional.of(of(input.trim()));
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {}
        return Optional.empty();
    }

    private Position(final int x, final int y) {
        this.x = Coordinate.of(x);
        this.y = Coordinate.of(y);
    }

    public boolean testForward(final Direction dir, final int steps) {
        final int targetX = this.x.value() + dir.offsetX * steps;
        final int targetY = this.y.value() + dir.offsetY * steps;
        return (Coordinate.MIN <= targetX && targetX < Coordinate.MAX) && (Coordinate.MIN <= targetY && targetY < Coordinate.MAX);
    }

    public boolean testForward(final Direction dir) {
        return testForward(dir, 1);
    }

    public Position moveForward(final Direction dir, final int steps) {
        return CACHE[this.x.value() + dir.offsetX * steps][this.y.value() + dir.offsetY * steps];
    }

    public Position moveForward(final Direction dir) {
        return moveForward(dir, 1);
    }

    public Optional<Position> moveForwardSafe(final Direction dir, final int steps) {
        return (testForward(dir, steps)) ? Optional.of(moveForward(dir, steps)) : Optional.empty();
    }

    public Optional<Position> moveForwardSafe(final Direction dir) {
        return moveForwardSafe(dir, 1);
    }

    public Optional<Position> move(final int x, final int y) {
        final int targetX = this.x.value() + x;
        final int targetY = this.y.value() + y;
        return ((Coordinate.MIN <= targetX && targetX < Coordinate.MAX) && (Coordinate.MIN <= targetY && targetY < Coordinate.MAX))
        ? Optional.of(CACHE[this.x.value() + x][this.y.value() + y])
        : Optional.empty();
    }

    public int get1DCoord() {
        return this.y.value() * Coordinate.MAX + this.x.value();
    }

    public Coordinate x() {
        return x;
    }

    public Coordinate y() {
        return y;
    }

    @Override
    public String toString() {
        return x.convertToStringX() + y.convertToStringY();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position rhs = (Position) o;
        return this.x.value() == rhs.x.value() &&
                this.y.value() == rhs.y.value();
    }

    @Override
    public int compareTo(final Position rhs) {
        return this.get1DCoord() - rhs.get1DCoord();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}