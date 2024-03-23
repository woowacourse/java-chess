package chess.domain.position;

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
        return file == other.file || rank == other.rank;
    }

    public boolean isFileSameWith(FileRankPosition other) {
        return file == other.file;
    }

    public boolean isRankSameWith(FileRankPosition other) {
        return rank == other.rank;
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
