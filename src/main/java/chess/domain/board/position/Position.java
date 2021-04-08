package chess.domain.board.position;

import chess.domain.direction.Direction;

import java.util.Objects;

public class Position {
    private static final int POSITION_VALID_SIZE = 2;

    private final Horizontal horizontal;
    private final Vertical vertical;

    private Position(final Horizontal horizontal, final Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    private Position(final String[] inputs) {
        this(Horizontal.parse(inputs[0]), Vertical.parse(inputs[1]));
    }

    private Position(final String input) {
        this(input.split(""));
    }

    public static Position of(final Horizontal horizontal, final Vertical vertical) {
        return new Position(horizontal, vertical);
    }

    public static Position of(final String input) {
        if (input.split("").length != POSITION_VALID_SIZE) {
            throw new IllegalArgumentException("입력값이 올바른 위치가 아닙니다.");
        }
        return new Position(input);
    }

    public boolean isValidPosition(final Direction direction) {
        return !this.isEscapePosition(direction);
    }

    public boolean isEscapePosition(final Direction direction) {
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

        throw new IllegalArgumentException("체스에 존재하지 않는 이동 방향 이라서 거리를 측정할 수 없습니다.");
    }

    public Position next(final Direction direction) {
        return new Position(horizontal.add(direction.getX()), vertical.add(direction.getY()));
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
        return vertical;
    }

    public int getHorizontalIndex() {
        return horizontal.getIndex();
    }

    public int getVerticalIndex() {
        return vertical.getIndex();
    }

    public String parseString() {
        String horizontal = this.horizontal.name().toLowerCase();
        int vertical = this.vertical.getIndex();
        return horizontal + vertical;
    }

    @Override
    public boolean equals(final Object o) {
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
    public String toString() {
        return vertical.getIndex() + " " + horizontal.getIndex();
    }
}