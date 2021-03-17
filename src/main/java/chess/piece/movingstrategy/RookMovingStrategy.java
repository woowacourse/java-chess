package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Direction;
import java.util.List;

public class RookMovingStrategy implements MovingStrategy {
    static final int LENGTH = 7;

    private final List<Direction> rooksDirection = Direction.axisDirection();

    @Override
    public Direction findMovableDirection(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return rooksDirection.stream()
            .filter(direction -> direction.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getDirectionLength() {
        return LENGTH;
    }
}
