package chess.domain.board.position;

import chess.domain.direction.Direction;

import java.util.Objects;

public class Position {
    private final Vertical vertical;
    private final Horizontal horizontal;

    public Position(final Vertical vertical, final Horizontal horizontal) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position(final String[] vh) {
        this(Vertical.parse(vh[0]), Horizontal.parse(vh[1]));
    }

    public Position(final String s) {
        this(s.split(""));
    }

    public static Position of(final String input) {
        if (input.split("").length != 2) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }

        return new Position(input);
    }

    public boolean isStraight(final Position other) {
        return this.horizontal.equals(other.horizontal) || this.vertical.equals(other.vertical);
    }

    public boolean isDiagonal(final Position other) {
        return this.horizontal.getDistance(other.horizontal)
                == this.vertical.getDistance(other.vertical);
    }

    public int getDistance(final Position other) {
        if (isStraight(other)) {
            return this.horizontal.getDistance(other.horizontal)
                    + this.vertical.getDistance(other.vertical);
        }

        if (isDiagonal(other)) {
            return this.horizontal.getDistance(other.horizontal);
        }

        throw new IllegalArgumentException();
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }

    public Position next(final Direction direction, final int distance) {
        return new Position(vertical.add(direction.getX() * distance), horizontal.add(direction.getY() * distance));
    }

    @Override
    public String toString() {
        return vertical.getIndex() + " " + horizontal.getIndex();
    }
}
