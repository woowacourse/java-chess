package chess.domain.board.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position {

    private static final List<Position> positionPool = new ArrayList<>();

    private final File file;
    private final Rank rank;

    Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        Position findPosition = findPosition(file, rank);

        if (findPosition == null) {
            Position createdPosition = new Position(file, rank);
            positionPool.add(createdPosition);

            return createdPosition;
        }

        return findPosition;
    }

    public static Position of(int file, int rank) {
        return of(File.of(file), Rank.of(rank));
    }

    public static Position of(String fileRank) {
        File file = File.of(fileRank.substring(0, 1));
        Rank rank = Rank.of(fileRank.substring(1, 2));

        return of(file, rank);
    }

    private static Position findPosition(File file, Rank rank) {
        return positionPool
                .stream()
                .filter(position -> position.file == file && position.rank == rank)
                .findAny()
                .orElse(null);
    }

    public int rankDisplacement(Position other) {
        return rank.displacement(other.rank);
    }

    public int fileDisplacement(Position other) {
        return file.displacement(other.file);
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
    }

    public boolean isSameRank(Position other) {
        return other.isRankOf(rank);
    }

    public boolean isSameFileAndRank(File file, Rank rank) {
        return this.file == file && this.rank == rank;
    }

    public boolean isRankOf(Rank otherRank) {
        return rank == otherRank;
    }

    public int rankDistance(Position other) {
        return Math.abs(rank.displacement(other.rank));
    }

    public int fileDistance(Position other) {
        return Math.abs(file.displacement(other.file));
    }

    public boolean isDiagonal(Position other) {
        return this.fileDistance(other) == this.rankDistance(other);
    }

    public boolean isCross(Position other) {
        return this.isSameRank(other) || this.isSameFile(other);
    }

    public boolean isAllDirectional(Position other) {
        return this.isDiagonal(other) || this.isCross(other);
    }

    public List<Position> findPositionsToMove(Position other) {
        List<File> traceFileGroup = File.traceGroup(this.file, other.file);
        List<Rank> traceRankGroup = Rank.traceGroup(this.rank, other.rank);

        return possiblePositions(traceRankGroup.listIterator(), traceFileGroup.listIterator());
    }

    private List<Position> possiblePositions(ListIterator<Rank> rankIterator, ListIterator<File> fileIterator) {
        List<Position> positions = new ArrayList<>();

        if (isOnlyMoveFile(rankIterator)) {
            fileIterator.forEachRemaining(file -> positions.add(Position.of(file, this.rank)));
            return positions;
        }

        if (isOnlyMoveRank(fileIterator)) {
            rankIterator.forEachRemaining(rank -> positions.add(Position.of(this.file, rank)));
            return positions;
        }

        while (rankIterator.hasNext()) {
            fileIterator.forEachRemaining(file -> positions.add(Position.of(file, rankIterator.next())));
        }

        return getPositionsAllDirectional(positions);
    }

    private List<Position> getPositionsAllDirectional(List<Position> positions) {
        return positions.stream()
            .filter(this::isAllDirectional)
            .collect(Collectors.toList());
    }

    private boolean isOnlyMoveRank(ListIterator<File> fileIterator) {
        return !fileIterator.hasNext();
    }

    private boolean isOnlyMoveFile(ListIterator<Rank> rankIterator) {
        return !rankIterator.hasNext();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position)o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
