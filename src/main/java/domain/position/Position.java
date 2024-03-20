package domain.position;

import domain.movement.Direction;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction getDirection(Position target) {
        validateSamePosition(target);
        int rankDiff = target.rank.subtract(this.rank);
        int fileDiff = target.file.subtract(this.file);
        return Direction.of(rankDiff, fileDiff);
    }

    private void validateSamePosition(Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("동일한 위치입니다.");
        }
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
