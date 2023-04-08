package domain.piece;

import dao.Movement;
import domain.Turn;
import domain.point.Direction;
import domain.point.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    public King(Turn turn) {
        super(turn);
    }

    public static final int SCORE = 0;

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.UP, 1);
        movableRange.put(Direction.DOWN, 1);
        movableRange.put(Direction.LEFT, 1);
        movableRange.put(Direction.RIGHT, 1);
        movableRange.put(Direction.LEFT_UP, 1);
        movableRange.put(Direction.LEFT_DOWN, 1);
        movableRange.put(Direction.RIGHT_UP, 1);
        movableRange.put(Direction.RIGHT_DOWN, 1);
        return movableRange;
    }

    @Override
    protected List<Point> findSpecializedPoints(Movement movement, Map<Point, Piece> status) {
        return List.of();
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public float getScore(List<Piece> line) {
        return SCORE;
    }

    @Override
    protected String getInitial() {
        return "k";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass()
                && turn == ((King) obj).turn;
    }
}
