package model;


import model.board.Board;

import java.util.Objects;

public class Coordinate {
    private int value;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = MIN_VALUE + Board.SIZE - 1;

    private Coordinate(int value) {
        this.value = value;
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
