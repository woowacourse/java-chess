package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.List;


public class Rook extends Piece {

    private static final String name = "r";
    private static final List<Direction> movableDirection = List.of(Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT);

    public Rook(Color color) {
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
