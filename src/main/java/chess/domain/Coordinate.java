package chess.domain;

import chess.domain.exceptions.CoordinateRangeException;

import java.util.Objects;

public class Coordinate {
    private final int coordinate;

    public Coordinate(int coordinate) {
        validCoordinate(coordinate);
        this.coordinate = coordinate;
    }

    private void validCoordinate(final int coordinate) {
        if (coordinate < 1 || coordinate > 8) {
            throw new CoordinateRangeException("좌표 범위는 1~8입니다.");
        }
    }

    public Coordinate(char coordinate) {
        int converted = coordinate - 96;
        validCoordinate(converted);
        this.coordinate = converted;
    }

    public int add(int operand) {
        return this.coordinate + operand;
    }

    public int subtract(Coordinate other) {
        return this.coordinate - other.coordinate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate that = (Coordinate) o;
        return coordinate == that.coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
