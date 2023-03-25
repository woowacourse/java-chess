package chess.domain.move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Direction {
    RIGHT,
    UP,
    LEFT,
    DOWN;

    public static final Set<Direction> HORIZONTALS = Set.of(LEFT, RIGHT);
    public static final Set<Direction> VERTICALS = Set.of(UP, DOWN);

    public boolean isHorizontal() {
        return HORIZONTALS.contains(this);
    }

    public boolean isVertical() {
        return VERTICALS.contains(this);
    }

    public List<Direction> repeat(long times) {
        List<Direction> directions = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            directions.add(this);
        }
        return directions;
    }
}
