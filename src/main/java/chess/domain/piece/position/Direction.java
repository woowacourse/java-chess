package chess.domain.piece.position;

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
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Direction> knight(Position current) {
        //현재 포지션에 따라서 갈수있는 방향이 달라지는거예요.
        //up과 down, left, right의 속성들을 모두 분리해야할까?
        //


        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UpUpRight);
        directions.add(Direction.UpRightRight);
        directions.add(Direction.DownRightRight);
        directions.add(Direction.DownDownRight);
        directions.add(Direction.DownDownLeft);
        directions.add(Direction.DownLeftLeft);
        directions.add(Direction.UpLeftLeft);
        directions.add(Direction.UpUpLeft);
        return directions;
    }

    public static List<Direction> upDownLeftRight() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.Up);
        directions.add(Direction.Down);
        directions.add(Direction.Left);
        directions.add(Direction.Right);
        return directions;
    }

    public static List<Direction> diagonal() { //diagonal
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

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
