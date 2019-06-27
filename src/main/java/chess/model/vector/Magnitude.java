package chess.model.vector;

import chess.model.board.Coordinate;

import java.util.List;
import java.util.Objects;

public class Magnitude {
    private int magnitude;

    public Magnitude(List<Coordinate> coordinates, Direction direction) {
        validateMagnitudeParameters(coordinates, direction);

        Coordinate sourceCoordinateX = coordinates.get(0);
        Coordinate sourceCoordinateY = coordinates.get(1);
        Coordinate targetCoordinateX = coordinates.get(2);
        Coordinate targetCoordinateY = coordinates.get(3);

        if ((direction == Direction.NORTH) || (direction == Direction.SOUTH)) {
            this.magnitude = Math.abs(targetCoordinateY.calculateDistance(sourceCoordinateY));
            return;
        }

        if ((direction == Direction.EAST) || (direction == Direction.WEST)) {
            this.magnitude = Math.abs(targetCoordinateX.calculateDistance(sourceCoordinateX));
            return;
        }

        if ((direction == Direction.SOUTHEAST) || (direction == Direction.SOUTHWEST)
                || (direction == Direction.NORTHEAST) || (direction == Direction.NORTHWEST)) {
            this.magnitude = Math.abs(targetCoordinateX.calculateDistance(sourceCoordinateX));
            return;
        }

        this.magnitude = 0;
    }

    private void validateMagnitudeParameters(List<Coordinate> coordinates, Direction direction) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }

        if (Objects.isNull(direction)) {
            throw new NullPointerException();
        }
    }

    public int getMagnitude() {
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
