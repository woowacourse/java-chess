package model;


import model.board.Board;

import java.util.Objects;

public class Coordinate {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = MIN_VALUE + Board.SIZE - 1;

    private int value;

    private Coordinate(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (MIN_VALUE > value || value > MAX_VALUE) {
            throw new IllegalArgumentException("좌표점의 범위는 0에서 7까지입니다.");
        }
    }

    static Coordinate of(int value) {
        return new Coordinate(value);
    }

    boolean isValid() {
        return MIN_VALUE <= value && value <= MAX_VALUE;
    }

    int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
