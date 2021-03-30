package chess.domain.board.position;

import chess.domain.direction.Direction;

import java.util.Objects;

public class Position {
    private static final int POSITION_VALID_SIZE = 2;

    private final Vertical vertical;
    private final Horizontal horizontal;

    private Position(final Vertical vertical, final Horizontal horizontal) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    private Position(final String[] inputs) {
        this(Vertical.parse(inputs[0]), Horizontal.parse(inputs[1]));
    }

    private Position(final String input) {
        this(input.split(""));
    }

    public static Position of(final Vertical vertical, final Horizontal horizontal) {
        return new Position(vertical, horizontal);
    }

    public static Position of(final String input) {
        if (input.split("").length != POSITION_VALID_SIZE) {
            throw new IllegalArgumentException("입력값이 올바른 위치가 아닙니다.");
        }
        return new Position(input);
    }

    public boolean isValidPosition(Direction direction) {
        return !this.isEscapePosition(direction);
    }

    public boolean isEscapePosition(Direction direction) {
        try {
            this.next(direction);
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

    public boolean isStraight(final Position other) {
        return this.horizontal.equals(other.horizontal) || this.vertical.equals(other.vertical);
    }

    public boolean isDiagonal(final Position other) {
        return this.horizontal.getDistance(other.horizontal)
                == this.vertical.getDistance(other.vertical);
    }

    public boolean isKnightDirection(final Position other) {
        int distanceHorizontal = this.horizontal.getDistance(other.horizontal);
        int distanceVertical = this.vertical.getDistance(other.vertical);
        return ((distanceHorizontal % 2) == 0 && (distanceHorizontal / 2) == distanceVertical)
                || ((distanceVertical % 2) == 0 && (distanceVertical / 2) == distanceHorizontal);
    }

    public int getDistance(final Position other) {
        if (isStraight(other)) {
            return this.horizontal.getDistance(other.horizontal)
                    + this.vertical.getDistance(other.vertical);
        }

        if (isDiagonal(other)) {
            return this.horizontal.getDistance(other.horizontal);
        }

        if (isKnightDirection(other)) {
            return (this.horizontal.getDistance(other.horizontal)
                    + this.vertical.getDistance(other.vertical)) / 3;
        }

        throw new IllegalArgumentException("직선이나 대각선 경로가 아니라서 거리를 측정할 수 없습니다.");
    }

    public Position next(final Direction direction) {
        return new Position(vertical.add(direction.getX()), horizontal.add(direction.getY()));
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

    @Override
    public String toString() {
        return vertical.getIndex() + " " + horizontal.getIndex();
    }
}