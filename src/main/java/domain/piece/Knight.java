package domain.piece;

import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public abstract class Knight extends Piece {
    @Override
    public Map<Direction, Integer> getMovableRange() {
        HashMap<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.UP_LEFT_L, 1);
        movableRange.put(Direction.UP_RIGHT_L, 1);
        movableRange.put(Direction.RIGHT_UP_L, 1);
        movableRange.put(Direction.RIGHT_DOWN_L, 1);
        movableRange.put(Direction.DOWN_LEFT_L, 1);
        movableRange.put(Direction.DOWN_RIGHT_L, 1);
        movableRange.put(Direction.LEFT_UP_L, 1);
        movableRange.put(Direction.LEFT_DOWN_L, 1);
        return movableRange;
    }
}
