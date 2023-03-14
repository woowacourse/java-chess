package chess.domain;

public final class Position {

    private final int rank;
    private final int file;

    public Position(final int rank, final int file) {
        validatePosition(rank, file);
        this.rank = rank;
        this.file = file;
    }

    private void validatePosition(final int rank, final int file) {
        if (rank > 7 || file > 7 || rank < 0 || file < 0) {
            throw new IllegalArgumentException("기물 위치는 최소 0 최대 7");
        }
    }
}
