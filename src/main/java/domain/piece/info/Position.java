package domain.piece.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position next(final int fileDelta, final int rankDelta) {
        return new Position(File.of(file.toIndex() + fileDelta), Rank.of(rank.toIndex() + rankDelta));
    }

    public int toFileIndex() {
        return file.toIndex();
    }

    public int toRankIndex() {
        return rank.toIndex();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
