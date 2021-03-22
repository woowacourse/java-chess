package chess.domain.board.position;

import chess.domain.piece.rule.Direction;

import java.util.Objects;

public class Position {
    private final Vertical vertical;
    private final Horizontal horizontal;

    public Position(Vertical vertical, Horizontal horizontal) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position(String[] fs) {
        this(Vertical.indexOf(fs[0]), Horizontal.indexOf(fs[1]));
    }

    public Position(String s) {
        this(s.split(""));
    }

    public static Position of(String input) {
        validatePosition(input);
        return new Position(input);
    }

    private static void validatePosition(String input) {
        if (input.split("").length != 2) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }
    }

    public boolean isStraight(Position other) {
        return this.horizontal.equals(other.horizontal) || this.vertical.equals(other.vertical);
    }

    public boolean isDiagonal(Position other) {
        return this.horizontal.getDistance(other.horizontal)
                == this.vertical.getDistance(other.vertical);
    }

    public int getDistance(Position other) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }

    public Position next(Direction direction, int distance) {
        return new Position(vertical.add(direction.getX() * distance), horizontal.add(direction.getY() * distance));
    }

    @Override
    public String toString() {
        return vertical.getIndex() + " " + horizontal.getIndex();
    }
}
