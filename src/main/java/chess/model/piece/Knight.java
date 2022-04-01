package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Knight extends PointMovingPiece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "n";
    }

    @Override
    public List<Direction> getDirection() {
        return List.of(
                Direction.NNE,
                Direction.NNW,
                Direction.SSE,
                Direction.SSW,
                Direction.EEN,
                Direction.EES,
                Direction.WWN,
                Direction.WWS);
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 2.5;
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
