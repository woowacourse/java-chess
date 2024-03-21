package domain.position;

import domain.game.Direction;
import domain.game.DirectionVector;
import java.util.Objects;

public class Position {
    private File file;
    private Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final Position other) {
        this.file = other.file;
        this.rank = other.rank;
    }

    public void move(final Direction direction) {
        int dFile = direction.getFileStepSize();
        int dRank = direction.getRankStepSize();
        file = file.add(dFile);
        rank = rank.add(dRank);
    }

    public DirectionVector subtract(Position target) {
        return new DirectionVector(file.subtract(target.file), rank.subtract(target.rank));
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
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
