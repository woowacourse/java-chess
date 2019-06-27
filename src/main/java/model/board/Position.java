package model.board;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {
    private static final Position[][] CACHE = IntStream.range(Coord.MIN, Coord.MAX)
                                                        .mapToObj(x ->
                                                                IntStream.range(Coord.MIN, Coord.MAX)
                                                                        .mapToObj(y -> new Position(x, y))
                                                                        .toArray(Position[]::new)
                                                        ).toArray(Position[][]::new);
    private static final Pattern validator = Pattern.compile("\\s*[a-zA-Z][0-9]\\s*");

    private final Coord x;
    private final Coord y;

    public static Position of(final String position) {
        return CACHE[position.substring(0, 1).toLowerCase().charAt(0) - 'a'][Integer.parseInt(position.substring(1)) - 1];
    }

    public static Position of(final Coord x, final Coord y) {
        return CACHE[x.val()][y.val()];
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
        this.x = Coord.of(x);
        this.y = Coord.of(y);
    }

    public boolean testForward(final Direction dir, final int steps) {
        final int targetX = this.x.val() + dir.offsetX * steps;
        final int targetY = this.y.val() + dir.offsetY * steps;
        return (Coord.MIN <= targetX && targetX < Coord.MAX) && (Coord.MIN <= targetY && targetY < Coord.MAX);
    }

    public boolean testForward(final Direction dir) {
        return testForward(dir, 1);
    }

    public Position moveForward(final Direction dir, final int steps) {
        return CACHE[this.x.val() + dir.offsetX * steps][this.y.val() + dir.offsetY * steps];
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
        final int targetX = this.x.val() + x;
        final int targetY = this.y.val() + y;
        return ((Coord.MIN <= targetX && targetX < Coord.MAX) && (Coord.MIN <= targetY && targetY < Coord.MAX))
        ? Optional.of(CACHE[this.x.val() + x][this.y.val() + y])
        : Optional.empty();
    }

    public Coord x() {
        return x;
    }

    public Coord y() {
        return y;
    }

    @Override
    public String toString() {
        return x.convertToStringX() + y.convertToStringY();
    }

    public int ordinal() {
        return this.y.val() * Coord.MAX + this.x.val();
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
        return this.x.val() == rhs.x.val() &&
                this.y.val() == rhs.y.val();
    }

    @Override
    public int compareTo(final Position rhs) {
        return this.ordinal() - rhs.ordinal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}