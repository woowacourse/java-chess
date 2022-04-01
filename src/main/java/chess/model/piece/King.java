package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class King extends PointMovingPiece {

    private static final String NAME = "k";
    private static final double POINT = 0;

    public King(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return NAME;
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
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
