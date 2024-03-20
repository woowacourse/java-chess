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

    public List<Position> route(Position resource, Position target) {
        Direction direction = resource.getDirection(target);
        if (!type.isMovable(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        List<Position> positions = new ArrayList<>();
        Position current = resource.next(direction);
        while (!current.equals(target)) {
            positions.add(current);
            current = current.next(direction);
        }
        if (!type.isValidMoveCount(positions.size() + 1)) {
            throw new IllegalArgumentException("이동할 수 없는 거리입니다.");
        }
        return positions;
    }
}
