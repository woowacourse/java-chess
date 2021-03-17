package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Direction;
import java.util.List;

public class BishopMovingStrategy implements MovingStrategy {
    private static final int LENGTH = 7;

    private final List<Direction> bishopDirection = Direction.diagonalDirection();

    @Override
    public Direction findMovableDirection(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);


        return bishopDirection.stream()
            .filter(direction -> direction.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getDirectionLength() {
        return LENGTH;
    }
}
