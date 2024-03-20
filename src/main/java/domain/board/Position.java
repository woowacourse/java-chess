package domain.board;

import domain.piece.info.Direction;
import domain.piece.info.File;
import domain.piece.info.Rank;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int fileIndex() {
        return file.index();
    }

    public int rankIndex() {
        return rank.index();
    }

    public Position next(final Direction direction) {
        return new Position(File.of(fileIndex() + direction.file()),
                Rank.of(rankIndex() + direction.rank()));
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
