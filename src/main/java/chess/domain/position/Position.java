package chess.domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int calculateFileGap(Position target) {
        return this.file.compareTo(target.file);
    }

    public int calculateRankGap(Position target) {
        return this.rank.getDifference(target.rank);
    }

    public int getFile() {
        return this.file.getNumber();
    }

    public int getRank() {
        return this.rank.getSymbol();
    }

    @Override
    public String toString() {
        return file.name() + rank.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
