package domain.piece.queen;

import domain.piece.Piece;
import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public abstract class Queen extends Piece {

    public static final float SCORE = 9f;

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.UP, 8);
        movableRange.put(Direction.DOWN, 8);
        movableRange.put(Direction.LEFT, 8);
        movableRange.put(Direction.RIGHT, 8);
        movableRange.put(Direction.LEFT_UP, 8);
        movableRange.put(Direction.LEFT_DOWN, 8);
        movableRange.put(Direction.RIGHT_UP, 8);
        movableRange.put(Direction.RIGHT_DOWN, 8);
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
