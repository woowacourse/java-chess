package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Pawn extends PawnMovingPiece {

    private static final String NAME = "p";
    private static final double POINT = 1;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public List<Direction> getDirection() {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH);
        }
        return List.of(Direction.NORTH);
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
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
