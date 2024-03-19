package chess.domain;

import java.util.Set;

public class Rook {
    private static Set<Direction> directions = Direction.getFourDirection();

    private Position position;
    private final Color color;

    public Rook(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Set<Position> findMovablePositions(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.forwardToDirection(direction, destination);
    }


    public boolean isSameColor(Color other) {
        return color == other;
    }

    public void update(Position destination) {
        this.position = destination;
    }
}
