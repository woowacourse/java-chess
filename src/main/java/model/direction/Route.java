package model.direction;

import model.position.Position;

import java.util.List;

public record Route(Direction direction, List<Position> positions) {

    public boolean contains(final Position position) {
        return positions.contains(position);
    }

    public List<Position> positions() {
        return List.copyOf(positions);
    }

    public Direction direction() {
        return direction;
    }
}
