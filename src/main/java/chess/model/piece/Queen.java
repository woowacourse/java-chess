package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Queen extends LinearMovingPiece {

    private static final double POINT = 9;

    public Queen(Color color) {
        super(color);
    }

    public Queen(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public String name() {
        return PieceType.q.name();
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
        return List.of(
                Direction.EAST,
                Direction.WEST,
                Direction.SOUTH,
                Direction.NORTH,
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST
        );
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
