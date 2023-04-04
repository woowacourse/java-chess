package domain.piece.knight;

import domain.piece.Piece;
import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public abstract class Knight extends Piece {

    public static final float SCORE = 2.5f;

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
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

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isWhitePawn() {
        return false;
    }

    @Override
    public boolean isBlackPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public float getScore() {
        return SCORE;
    }
}
