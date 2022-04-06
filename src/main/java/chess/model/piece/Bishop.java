package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Bishop extends LinearMovingPiece {

    private static final double POINT = 3;

    public Bishop(Color color) {
        super(color);
    }

    public Bishop(Color color, int squareId) {
        super(color, squareId);
    }

    public Bishop(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public String name() {
        return PieceType.b.name();
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public List<Direction> getDirection() {
        return List.of(Direction.NORTH_WEST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
