package chess.domain.piece.position;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_LEFT(-1, 1),

    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),
    UP_LEFT_LEFT(-2, 1),
    UP_UP_LEFT(-1, 2),

    UP_UP(0, 2),
    DOWN_DOWN(0, -2)
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Direction> upDownLeftRight() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        return directions;
    }

    public static List<Direction> diagonal() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
        return directions;
    }

    public static List<Direction> all() {
        List<Direction> directions = new ArrayList<>();
        directions.addAll(upDownLeftRight());
        directions.addAll(diagonal());
        return directions;
    }

    public static List<Direction> leftRight() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        return directions;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
