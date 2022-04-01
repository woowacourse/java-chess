package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Bishop extends LinearMovingPiece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "b";
    }

    @Override
    public List<Direction> getDirection() {
        return List.of(Direction.NORTH_WEST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 3;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
