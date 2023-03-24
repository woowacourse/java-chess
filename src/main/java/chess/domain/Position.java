package chess.domain;

import java.util.List;
import java.util.Objects;

public class Position {

    private static final String OUT_OF_BOUNDS_ERROR_MESSAGE = "말의 위치 범위를 벗어났습니다.";
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;

    private final int file;
    private final int rank;

    private Position(final int file, final int rank) {
        validate(file, rank);
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final int file, final int rank) {
        return new Position(file, rank);
    }

    public static Position from(List<Integer> coordinate) {
        return new Position(coordinate.get(POSITION_FILE_INDEX),
                coordinate.get(POSITION_RANK_INDEX));
    }

    private void validate(int file, int rank) {
        if (isNotInRange(file, rank)) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_ERROR_MESSAGE);
        }
    }

    private boolean isNotInRange(final int file, final int rank) {
        return isValueNotInRange(file) || isValueNotInRange(rank);
    }

    private boolean isValueNotInRange(final int positionValue) {
        return positionValue < MINIMUM || positionValue > MAXIMUM;
    }

    public Position findNextPosition(final Direction direction) {
        int nextFile = direction.nextFile(file);
        int nextRank = direction.nextRank(rank);
        if (isNotInRange(nextRank, nextFile)) {
            return null;
        }
        return Position.of(nextFile, nextRank);
    }

    public int fileIncreaseFrom(Position destination) {
        return destination.file - file;
    }

    public int rankIncreaseFrom(Position destination) {
        return destination.rank - rank;
    }

    public boolean isInExpectedRank(int rank) {
        return this.rank == rank;
    }

    public boolean isInSameFile(Position other) {
        return this.file == other.file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

}
