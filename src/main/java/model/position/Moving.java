package model.position;

import static model.position.Direction.DIRECTION_FILE;
import static model.position.Direction.DIRECTION_RANK;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Moving {
    private final Position currentPosition;
    private final Position nextPosition;

    public Moving(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public boolean isHorizontal() {
        final int currentRank = currentPosition.getRankIndex();
        final int nextRank = nextPosition.getRankIndex();

        return currentRank == nextRank;
    }

    public boolean isVertical() {
        final int currentFile = currentPosition.getFileIndex();
        final int nextFile = nextPosition.getFileIndex();

        return currentFile == nextFile;
    }

    public boolean isDiagonal() {
        final int currentRank = currentPosition.getRankIndex();
        final int currentFile = currentPosition.getFileIndex();

        final int nextRank = nextPosition.getRankIndex();
        final int nextFile = nextPosition.getFileIndex();

        return Math.abs(currentRank - nextRank) == Math.abs(currentFile - nextFile);
    }

    public boolean isNotMoved() {
        return Objects.equals(currentPosition, nextPosition);
    }

    public boolean isAdjacent() {
        final int currentRank = currentPosition.getRankIndex();
        final int currentFile = currentPosition.getFileIndex();

        final int nextRank = nextPosition.getRankIndex();
        final int nextFile = nextPosition.getFileIndex();

        return Math.abs(nextRank - currentRank) <= 1 && Math.abs(nextFile - currentFile) <= 1;
    }

    public Set<Position> route() {
        final int currentRank = currentPosition.getRankIndex();
        final int currentFile = currentPosition.getFileIndex();
        final int index = Direction.from(currentPosition, nextPosition).getIndex();

        Set<Position> result = new HashSet<>();
        for (int i = 1; i < distance(); i++) {
            Rank rank = Rank.from(currentRank + (i * DIRECTION_RANK[index]));
            File file = File.from(currentFile + (i * DIRECTION_FILE[index]));
            result.add(new Position(file, rank));
        }
        return result;
    }

    private int distance() {
        final int currentRank = currentPosition.getRankIndex();
        final int currentFile = currentPosition.getFileIndex();

        final int nextRank = nextPosition.getRankIndex();
        final int nextFile = nextPosition.getFileIndex();

        return Math.max(Math.abs(currentRank - nextRank), Math.abs(currentFile - nextFile));
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    @Override
    public boolean equals(Object target) {
        if (target == this) {
            return true;
        }
        if (!(target instanceof Moving moving)) {
            return false;
        }
        return Objects.equals(currentPosition, moving.currentPosition) && Objects.equals(nextPosition,
                moving.nextPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, nextPosition);
    }

    @Override
    public String toString() {
        return "Moving[" +
                "currentPosition=" + currentPosition + ", " +
                "nextPosition=" + nextPosition + ']';
    }
}
