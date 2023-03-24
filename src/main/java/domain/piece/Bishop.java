package domain.piece;

import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public abstract class Bishop extends Piece {
    @Override
    public Map<Direction, Integer> getMovableRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.LEFT_DOWN, 8);
        movableRange.put(Direction.LEFT_UP, 8);
        movableRange.put(Direction.RIGHT_DOWN, 8);
        movableRange.put(Direction.RIGHT_UP, 8);
        return movableRange;
    }
}
