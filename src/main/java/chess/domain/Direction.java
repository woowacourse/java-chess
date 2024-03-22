package chess.domain;

import java.util.List;
import java.util.Set;

public enum Direction {
    DOWN,
    LEFT,
    RIGHT,
    UP_RIGHT,
    UP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT,
    UP;

    public boolean isDiagonal() {
        return diagonal().contains(this);
    }

    public boolean isVertical() {
        return Set.of(UP, DOWN).contains(this);
    }

    public static List<Direction> all(){
        return List.of(values());
    }

    public static List<Direction> diagonal(){
        return List.of(Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    }

    public static List<Direction> cross(){
        return List.of(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);
    }
}
