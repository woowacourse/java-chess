package chess.domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String position) {
        this(File.fromSymbol(position.charAt(0)), Rank.fromSymbol(position.charAt(1)));
    }

    public Position move(final int fileIndex, final int rankIndex) {
        return new Position(this.file.addIndex(fileIndex), this.rank.addIndex(rankIndex));
    }

    public boolean isSameFile(final Position other) {
        return this.file.equals(other.file);
    }

    public boolean isSameRank(final Position other) {
        return this.rank.equals(other.rank);
    }

    public int getFileDistance(final Position other) {
        return this.file.getDistance(other.file);
    }

    public int getRankDistance(final Position other) {
        return this.rank.getDistance(other.rank);
    }

    public boolean isRankUpper(final Position other) {
        return this.rank.isUp(other.rank);
    }

    public boolean isFileRighter(final Position other) {
        return this.file.isRighter(other.file);
    }

    public boolean isRank(final Rank rank) {
        return this.rank.equals(rank);
    }

    public int getFileIndex() {
        return this.file.getIndex();
    }

    public int getRankIndex() {
        return this.rank.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
