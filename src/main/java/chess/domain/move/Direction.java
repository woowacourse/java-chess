package chess.domain.move;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    RIGHT(Axis.HORIZON),
    UP(Axis.VERTICAL),
    LEFT(Axis.HORIZON),
    DOWN(Axis.VERTICAL);

    private final Axis axis;

    Direction(Axis axis) {
        this.axis = axis;
    }

    public boolean isIn(Axis axis) {
        return this.axis == axis;
    }

    public List<Direction> repeat(long times) {
        List<Direction> directions = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            directions.add(this);
        }
        return directions;
    }
}
