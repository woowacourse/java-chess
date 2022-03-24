package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public Position(String rankFile) {
        this.rank = Rank.of(rankFile.substring(1, 2));
        this.file = File.of(rankFile.substring(0, 1));
    }

    public int rankDisplacement(Position other) {
        return rank.displacement(other.rank);
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
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

    public boolean isRankOf(Rank otherRank) {
        return rank == otherRank;
    }

    public boolean isSameRank(Position other) {
        return other.isRankOf(rank);
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

    public List<Position> traceGroup(Position other) {
        List<File> traceFileGroup = File.traceGroup(this.file, other.file);
        List<Rank> traceRankGroup = Rank.traceGroup(this.rank, other.rank);

        ListIterator<File> fileListIterator = traceFileGroup.listIterator();
        ListIterator<Rank> rankListIterator = traceRankGroup.listIterator();

        return possiblePositions(rankListIterator, fileListIterator);
    }

    public List<Position> possiblePositions(ListIterator<Rank> rankIterator, ListIterator<File> fileIterator) {
        List<Position> positions = new ArrayList<>();

        if (!rankIterator.hasNext()) {
            while (fileIterator.hasNext()) {
                positions.add(new Position(this.rank, fileIterator.next()));
            }
            return positions;
        }

        if (!fileIterator.hasNext()) {
            while (rankIterator.hasNext()) {
                positions.add(new Position(rankIterator.next(), this.file));
            }
            return positions;
        }

        while (rankIterator.hasNext()) {
            while (fileIterator.hasNext()) {
                Position position = new Position(rankIterator.next(), fileIterator.next());
                if (this.isAllDirectional(position)) {
                    positions.add(position);
                }
            }
            positions.add(new Position(rankIterator.next(), this.file));
        }

        return positions;
    }
}
