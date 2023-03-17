package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.List;

public class Bishop extends Piece {

    private static final String name = "b";
    private static final List<Direction> movableDirection = List.of(
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public Bishop(Color color) {
        super(name, color);
    }

    @Override
    public boolean isMovableAtOnce(int abs, int abs1) {
        return true;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return movableDirection.contains(direction);
    }
}
