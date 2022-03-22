package chess;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public File getFile() {
        return file;
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
