package chess.domain;

import java.util.Objects;

public class Coordinate {
    private final int coordinate;

    public Coordinate(int coordinate) {
        if (coordinate < 1 || coordinate > 8) {
            throw new IllegalArgumentException("세로 범위는 1~8입니다.");
        }
        this.coordinate = coordinate;
    }

    public Coordinate(char coordinate) {
        int converted = coordinate - 96;
        if (converted < 1 || converted > 8) {
            throw new IllegalArgumentException("세로 범위는 1~8입니다.");
        }
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
