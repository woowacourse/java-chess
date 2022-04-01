package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Rook extends LinearMovingPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "r";
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
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 5;
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
