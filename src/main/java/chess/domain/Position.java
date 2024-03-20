package chess.domain;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean canMoveNext(Direction direction) {
        int nextFile = this.file.getValue() + direction.getX();
        int nextRank = this.rank.getValue() + direction.getY();

        return file.isInRange(nextFile) && rank.isInRange(nextRank);
    }

    public Position next(Direction direction) {
        int x = direction.getX();
        int y = direction.getY();
        return new Position(file.add(x), rank.add(y));
    }

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position position)) {
            return false;
        }
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
