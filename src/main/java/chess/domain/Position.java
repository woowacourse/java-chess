package chess.domain;

import chess.domain.path.Direction;
import chess.view.File;
import chess.view.Rank;
import java.util.Objects;

public class Position {

    private static final String OUT_OF_BOUNDS_ERROR_MESSAGE = "말의 위치 범위를 벗어났습니다.";
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;
    private final int x;
    private final int y;

    private Position(final int x, final int y) {
        validate(x, y);
        this.x = x;
        this.y = y;
    }

    public static Position of(final int x, final int y) {
        return new Position(x, y);
    }

    public static Position from(final String fileRank) {
        return new Position(File.findByIndex(String.valueOf(fileRank.charAt(POSITION_FILE_INDEX))),
            Rank.findByIndex(String.valueOf(fileRank.charAt(POSITION_RANK_INDEX))));
    }

    private void validate(int x, int y) {
        if (isNotInRange(x, y)) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_ERROR_MESSAGE);
        }
    }

    private boolean isNotInRange(final int rank, final int file) {
        return isValueNotInRange(rank) || isValueNotInRange(file);
    }

    private boolean isValueNotInRange(final int positionValue) {
        return positionValue < MINIMUM || positionValue > MAXIMUM;
    }

    public Position findNextPosition(final Direction direction) {
        int nextFile = direction.moveFile(x);
        int nextRank = direction.moveRank(y);

        if (isNotInRange(nextFile, nextRank)) {
            return null;
        }

        return Position.of(nextFile, nextRank);
    }

    public boolean isOneStepForwardDiagonal(Position other) {
        return other.x - x == 1 && Math.abs(other.y - y) == 1;
    }

    public boolean isInExpectedRank(int rank) {
        return y == rank;
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
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

}
