package chess.domain.piece.position;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    Up(0, 1),
    UpRight(1, 1),
    Right(1, 0),
    DownRight(1, -1),
    Down(0, -1),
    DownLeft(-1, -1),
    Left(-1, 0),
    UpLeft(-1, 1),
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Direction> rook() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.Up);
        directions.add(Direction.Down);
        directions.add(Direction.Left);
        directions.add(Direction.Right);
        return directions;
    }

    public static List<Direction> bishop() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UpRight);
        directions.add(Direction.UpLeft);
        directions.add(Direction.DownRight);
        directions.add(Direction.DownLeft);
        return directions;
    }

    public static List<Direction> queen() {
        List<Direction> directions = new ArrayList<>();
        directions.addAll(rook());
        directions.addAll(bishop());
        return directions;
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
