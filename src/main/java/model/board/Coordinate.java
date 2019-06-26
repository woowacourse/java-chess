package model.board;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    static final int MIN = 0;
    static final int MAX = Board.WIDTH;
    private final int value;

    static Coordinate of(final int value) {
        validateRange(value);
        return new Coordinate(value);
    }

    private static void validateRange(int value) {
        if (value < MIN || MAX <= value) {
            throw new IllegalArgumentException("좌표 범위에서 벗어난 지점입니다.");
        }
    }

    private Coordinate(final int value) {
        this.value = value;
    }

    int value() {
        return value;
    }

    String convertToStringX() {
        return String.valueOf((char) (this.value + 'a'));
    }

    String convertToStringY() {
        return String.valueOf(value + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        final Coordinate rhs = (Coordinate) o;
        return value == rhs.value;
    }

    @Override
    public int compareTo(final Coordinate rhs) {
        return this.value - rhs.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}