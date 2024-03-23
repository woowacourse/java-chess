package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileRankPosition {
    private final File file;
    private final Rank rank;

    public FileRankPosition(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public FileRankPosition calculateVerticalReversedPosition() {
        return new FileRankPosition(file, rank.reverse());
    }

    public boolean isOrthogonalWith(FileRankPosition other) {
        if (this.equals(other)) {
            return false;
        }
        return file == other.file || rank == other.rank;
    }

    public boolean isDiagonalWith(FileRankPosition other) {
        if (this.equals(other)) {
            return false;
        }
        int fileDistance = file.calculateDistanceWith(other.file);
        int rankDistance = rank.calculateDistanceWith(other.rank);
        return fileDistance == rankDistance;
    }

    //TODO: 테스트 없음 후에 리팩토링 과정에서 개선
    public Direction directionTo(FileRankPosition target) {
        boolean destinationIsAbove = target.rank.isAbove(rank);
        return Direction.from(destinationIsAbove);
    }

    //TODO: 테스트 없음 후에 리팩토링 과정에서 개선
    public int squaredDistanceWith(FileRankPosition other) {
        int fileDistance = file.calculateDistanceWith(other.file);
        int rankDistance = rank.calculateDistanceWith(other.rank);
        return (int) Math.pow(fileDistance, 2) + (int) Math.pow(rankDistance, 2);
    }

    //TODO: 테스트 없음 후에 리팩토링 과정에서 개선
    public boolean isRankSameWith(FileRankPosition other) {
        return rank == other.rank;
    }

    public boolean isFurtherLeftThan(FileRankPosition other) {
        return file.isFurtherLeftThan(other.file);
    }

    public boolean isFurtherRightThan(FileRankPosition other) {
        return file.isFurtherRightThan(other.file);
    }

    public boolean isAbove(FileRankPosition other) {
        return rank.isAbove(other.rank);
    }

    public boolean isBelow(FileRankPosition other) {
        return rank.isBelow(other.rank);
    }

    public boolean isLeftLowerThan(FileRankPosition other) {
        return this.isBelow(other) && this.isFurtherLeftThan(other);
    }

    public boolean isLeftUpperThan(FileRankPosition other) {
        return this.isAbove(other) && this.isFurtherLeftThan(other);
    }

    public boolean isRightUpperThan(FileRankPosition other) {
        return this.isAbove(other) && this.isFurtherRightThan(other);
    }

    public boolean isRightLowerThan(FileRankPosition other) {
        return this.isBelow(other) && this.isFurtherRightThan(other);
    }

    public List<FileRankPosition> calculateSlidingPath(FileRankPosition destination) {
        BoardDirection boardDirection = BoardDirection.of(this, destination);
        FileRankPosition start = this;
        List<FileRankPosition> path = new ArrayList<>();
        while (!start.equals(destination)) {
            start = start.moveOneSpace(boardDirection);
            path.add(start);
        }
        path.remove(path.size() - 1);
        return path;
    }

    private FileRankPosition moveOneSpace(BoardDirection boardDirection) {
        File movedFile = file.move(boardDirection.getMoveOnceFileWeight());
        Rank movedRank = rank.move(boardDirection.getMoveOnceRankWeight());
        return new FileRankPosition(movedFile, movedRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileRankPosition that = (FileRankPosition) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
