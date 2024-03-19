package chess.domain;

import java.util.Set;

public class King {
    private static Set<Direction> directions = Direction.getEightDirection();

    private Position position;
    private final Color color;

    public King(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Set<Position> findMovablePositions(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (movable.contains(destination)) {
            return Set.of(destination);
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    public void update(Position destination) {
        this.position = destination;
    }
}
