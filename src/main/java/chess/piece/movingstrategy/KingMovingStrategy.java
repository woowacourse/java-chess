package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Direction;
import java.util.List;

public class KingMovingStrategy implements MovingStrategy {

    public static final int LENGTH = 1;
    private final List<Direction> kingsDirection = Direction.everyDirection();

    @Override
    public Direction findMovableDirection(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return kingsDirection.stream()
            .filter(direction -> direction.isRightDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getDirectionLength() {
        return LENGTH;
    }
}
