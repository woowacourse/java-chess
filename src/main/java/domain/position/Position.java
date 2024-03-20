package domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isDiagonal(Position target) {
        int fileDistance = this.file.distance(target.file);
        int rankDistance = this.rank.distance(target.rank);
        return fileDistance == rankDistance;
    }

    public boolean isStraight(Position target) {
        return this.file.isSame(target.file) || this.rank.isSame(target.rank);
    }

    public boolean isStraightDiagonal(Position target) {
        int fileDistance = this.file.distance(target.file);
        int rankDistance = this.rank.distance(target.rank);
        return (fileDistance == 1 && rankDistance == 2) || (fileDistance == 2 && rankDistance == 1);
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
