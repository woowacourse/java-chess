package domain;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return Objects.equals(rank, position.rank) && Objects.equals(file, position.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
