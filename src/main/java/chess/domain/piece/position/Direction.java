package chess.domain.piece.position;

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

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
