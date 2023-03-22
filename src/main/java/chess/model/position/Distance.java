package chess.model.position;

public class Distance {

    private final int file;
    private final int rank;

    public Distance(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction findDirection() {
        return Direction.findDirection(file, rank);
    }

    public int rank() {
        return rank;
    }

    public int file() {
        return file;
    }
}
