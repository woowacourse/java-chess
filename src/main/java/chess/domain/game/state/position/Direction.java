package chess.domain.game.state.position;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    Up(0, 1),
    Right(1, 0),
    Down(0, -1),
    Left(-1, 0),

    UpRight(1, 1),
    DownRight(1, -1),
    DownLeft(-1, -1),
    UpLeft(-1, 1),

    UpUpRight(1, 2),
    UpRightRight(2, 1),
    DownRightRight(2, -1),
    DownDownRight(1, -2),
    DownDownLeft(-1, -2),
    DownLeftLeft(-2, -1),
    UpLeftLeft(-2, 1),
    UpUpLeft(-1, 2),

    UpUp(0, 2),
    DownDown(0, -2)
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Direction> upDownLeftRight() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.Up);
        directions.add(Direction.Down);
        directions.add(Direction.Left);
        directions.add(Direction.Right);
        return directions;
    }

    public static List<Direction> diagonal() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UpRight);
        directions.add(Direction.UpLeft);
        directions.add(Direction.DownRight);
        directions.add(Direction.DownLeft);
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
        directions.add(Direction.Left);
        directions.add(Direction.Right);
        return directions;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
