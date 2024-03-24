package chess.domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String input) {
        this(File.fromSymbol(input.charAt(0)), Rank.from(input.charAt(1) - '0'));
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

    public boolean isRank(final Rank rank) {
        return this.rank.equals(rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
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
