package model.board;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {
    private static final Position[][] CACHE = IntStream.range(Coord.MIN, Coord.MAX)
                                                    .mapToObj(x ->
                                                            IntStream.range(Coord.MIN, Coord.MAX)
                                                                    .mapToObj(y -> new Position(x, y))
                                                                    .toArray(Position[]::new)
                                                    ).toArray(Position[][]::new);

    private final Coord x;
    private final Coord y;

    public static Position of(String position) {
        return CACHE[position.substring(0, 1).toLowerCase().charAt(0) - 'a'][Integer.parseInt(position.substring(1)) - 1];
    }

    public static Optional<Position> ofSafe(String input) {
        try {
            if (input.matches("\\s*[a-zA-Z][0-9]\\s*")) {
                return Optional.of(of(input.trim()));
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {}
        return Optional.empty();
    }

    private Position(int x, int y) {
        this.x = Coord.of(x);
        this.y = Coord.of(y);
    }

    public boolean testForward(Direction dir, int steps) {
        final int targetX = this.x.val() + dir.offsetX * steps;
        final int targetY = this.y.val() + dir.offsetY * steps;
        return (Coord.MIN <= targetX && targetX < Coord.MAX)
                && (Coord.MIN <= targetY && targetY < Coord.MAX);
    }

    public boolean testForward(Direction dir) {
        return testForward(dir, 1);
    }

    public Position moveForward(Direction dir, int steps) {
        return CACHE[this.x.val() + dir.offsetX * steps][this.y.val() + dir.offsetY * steps];
    }

    public Position moveForward(Direction dir) {
        return moveForward(dir, 1);
    }

    public Optional<Position> tryToMoveForward(Direction dir, int steps) {
        if (testForward(dir, steps)) {
            return Optional.of(moveForward(dir, steps));
        }
        return Optional.empty();
    }

    public Optional<Position> tryToMoveForward(Direction dir) {
        return tryToMoveForward(dir, 1);
    }

    public Optional<Position> move(int x, int y) {
        final int targetX = this.x.val() + x;
        final int targetY = this.y.val() + y;
        if (Coord.MIN <= targetX && targetX < Coord.MAX
                && Coord.MIN <= targetY && targetY < Coord.MAX) {
            return Optional.of(CACHE[this.x.val() + x][this.y.val() + y]);
        }
        return Optional.empty();
    }

    public int get1DCoord() {
        return this.y.val() * Coord.MAX + this.x.val();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position rhs = (Position) o;
        return this.x.val() == rhs.x.val() &&
                this.y.val() == rhs.y.val();
    }

    @Override
    public int compareTo(Position rhs) {
        return this.get1DCoord() - rhs.get1DCoord();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}