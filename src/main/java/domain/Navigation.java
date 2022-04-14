package domain;

import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Navigation {

    private final Position source;
    private final Position target;

    public Navigation(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public List<Position> route(Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position nextPosition = source;
        while (nextPosition != null && !nextPosition.equals(target)) {
            nextPosition = nextPosition.createNextPosition(direction);
            positions.add(nextPosition);
        }
        return positions;
    }
}
