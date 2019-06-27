package chess.model.board.vector;

import chess.model.board.Coordinate;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Vector {
    private Direction direction;
    private Magnitude magnitude;

    public Vector(List<Coordinate> coordinates) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }
        direction = Direction.findDirection(coordinates);
        magnitude = new Magnitude(coordinates, direction);
    }

    public boolean isEqualToDirection(Direction direction) {
        return this.direction.equals(direction);
    }

    public boolean isEqualToMagnitude(int magnitude) {
        return this.magnitude.isEqual(magnitude);
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMagnitude() {
        return magnitude.getMagnitude();
    }

    public boolean isMatch(Set<Direction> movableDirections) {
        return movableDirections.contains(direction);
    }
}
