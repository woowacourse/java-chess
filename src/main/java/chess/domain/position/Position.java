package chess.domain.position;

public record Position(int file, int rank) {

    private static final int MIN_POSITION_RANGE = 1;
    private static final int MAX_POSITION_RANGE = 8;

    public Position {
        validateRange(file);
        validateRange(rank);
    }

    private void validateRange(final int position) {
        if (position < MIN_POSITION_RANGE || position > MAX_POSITION_RANGE) {
            throw new IllegalArgumentException("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
        }
    }

    public boolean isMinimumFile() {
        return file == MIN_POSITION_RANGE;
    }

    public boolean isMaximumFile() {
        return file == MAX_POSITION_RANGE;
    }

    public boolean isMinimumRank() {
        return rank == MIN_POSITION_RANGE;
    }

    public boolean isMaximumRank() {
        return rank == MAX_POSITION_RANGE;
    }

    public boolean isNextPositionInRange(final Vector vector) {
        int nextFile = vector.getFileVector() + file;
        int nextRank = vector.getRankVector() + rank;
        return isNextMoveInRange(nextFile) && isNextMoveInRange(nextRank);
    }

    private boolean isNextMoveInRange(int nextPosition) {
        return nextPosition > 0 && nextPosition < 9;
    }
}
