package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Rook extends LinearMovingPiece {

    private static final String NAME = "r";
    private static final double POINT = 5;

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return NAME;
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
                Direction.NORTH
        );
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
