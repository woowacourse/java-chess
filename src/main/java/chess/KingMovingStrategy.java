package chess;

import java.util.List;

public class KingMovingStrategy implements MovingStrategy {

    private final List<Direction> kingsDirection = Direction.everyDirection();

    @Override
    public boolean canMove(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return kingsDirection.stream()
            .anyMatch(direction -> direction.isRightDirection(x, y));
    }
}
