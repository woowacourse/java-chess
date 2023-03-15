package chess.domain;

public final class Position {

    private final int rank;
    private final char file;

    private Position(final int rank, final char file) {
        validatePosition(rank, file);
        this.rank = rank;
        this.file = file;
    }

    public static Position from(final int rank, final char file) {
        return new Position(rank, file);
    }

    private void validatePosition(final int rank, final char file) {
        validateRankRange(rank);
        validateFileRange(file);
    }
    
    private void validateRankRange(final int rank) {
        if (isPieceFileOutOfRange(rank)) {
            throw new IllegalArgumentException("기물의 가로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
        }
    }

    private void validateFileRange(final char file) {
        if (isPieceRankOutOfRange(file)) {
            throw new IllegalArgumentException("기물의 세로 위치는 a부터 h까지 놓을 수 있습니다.");
        }
    }

    private static boolean isPieceFileOutOfRange(int piecePosition) {
        return piecePosition < 0 || 7 < piecePosition;
    }

    private boolean isPieceRankOutOfRange(int rank) {
        return rank < 97 || 104 < rank;
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    public Position changePosition(int rank) {
        return new Position(rank, this.file);
    }
}
