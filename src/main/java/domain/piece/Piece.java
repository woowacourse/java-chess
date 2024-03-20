package domain.piece;

import domain.movement.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Piece {
    private final Type type;
    private final Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public List<Position> route(Position resource, Position target) { // todo movable
        Direction direction = resource.getDirection(target);
        // direction  valid
        List<Position> positions = new ArrayList<>();
        Position current = resource.next(direction);
        while (!current.equals(target)) {
            positions.add(current);
            current = current.next(direction);
        }
        // move count valid
        return positions;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
