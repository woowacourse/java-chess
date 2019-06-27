package chess.model.board.vector;

import chess.model.board.Coordinate;

import java.util.List;
import java.util.Objects;

import static chess.model.board.vector.Direction.*;

public class Magnitude {
    private static final int KNIGHT_MAGNITUDE = 0;

    private int magnitude;

    public Magnitude(List<Coordinate> coordinates, Direction direction) {
        validateNull(coordinates, direction);

        this.magnitude = calculateMagnitude(coordinates, direction);
    }

    private int calculateMagnitude(List<Coordinate> coordinates, Direction direction) {
        Coordinate sourceCoordinateX = coordinates.get(0);
        Coordinate sourceCoordinateY = coordinates.get(1);
        Coordinate targetCoordinateX = coordinates.get(2);
        Coordinate targetCoordinateY = coordinates.get(3);

        if (isVertical(direction)) {
            return Math.abs(targetCoordinateY.calculateDistance(sourceCoordinateY));
        }
        if (isHorizontal(direction)) {
            return Math.abs(targetCoordinateX.calculateDistance(sourceCoordinateX));
        }
        if (isDiagonal(direction)) {
            return Math.abs(targetCoordinateX.calculateDistance(sourceCoordinateX));
        }
        return KNIGHT_MAGNITUDE;
    }

    private void validateNull(List<Coordinate> coordinates, Direction direction) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }

        if (Objects.isNull(direction)) {
            throw new NullPointerException();
        }
    }

    public boolean isEqual(int magnitude) {
        return this.magnitude == magnitude;
    }

    int getMagnitude() {
        return magnitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magnitude magnitude1 = (Magnitude) o;
        return magnitude == magnitude1.magnitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magnitude);
    }
}
