package chess.domain;

import java.util.Objects;

public class Coordinate {
    private final int coordinate;

    public Coordinate(int coordinate) {
        if (coordinate < 1 || coordinate > 8) {
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

    public Coordinate(char coordinate) {
        if (coordinate < 'a' || coordinate > 'h') {
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

    public int getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return coordinate == that.coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
