package chess.domain;

public final class Position {

    private final char rank;
    private final int file;

    public Position(final char rank, final int file) {
        validatePosition(rank, file);
        this.rank = rank;
        this.file = file;
    }

    private void validatePosition(final char rank, final int file) {
        validateRankRange(rank);
        validateFileRange(file);
    }
    
    private void validateRankRange(final char rank) {
        if (isPieceRankOutOfRange(rank)) {
            throw new IllegalArgumentException("기물의 가로 위치는 a부터 h까지 놓을 수 있습니다.");
        }
    }

    private void validateFileRange(final int file) {
        if (isPieceFileOutOfRange(file)) {
            throw new IllegalArgumentException("기물의 세로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
        }
    }

    private boolean isPieceRankOutOfRange(int rank) {
        return rank < 97 || 104 < rank;
    }

    private static boolean isPieceFileOutOfRange(int piecePosition) {
        return piecePosition < 0 || 7 < piecePosition;
    }
}
