package chess.domain;

import java.util.Set;

public enum Direction {
    DOWN,
    LEFT,
    RIGHT,
    TOP_RIGHT,
    TOP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT,
    TOP;

    public boolean isDiagonal() {
        return Set.of(TOP_RIGHT, TOP_LEFT, DOWN_RIGHT, DOWN_LEFT).contains(this);
    }
}
