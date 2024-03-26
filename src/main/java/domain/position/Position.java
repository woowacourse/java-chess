package domain.position;

import domain.game.Direction;
import domain.game.Vector;
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

    public Vector generateVectorToTargetPosition(final Position target) {
        return new Vector(file.subtract(target.file), rank.subtract(target.rank));
    }

    public Position move(final Direction direction) {
        int dFile = direction.getFileVector();
        int dRank = direction.getRankVector();
        return new Position(file.add(dFile), rank.add(dRank));
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

    public boolean isRank2() {
        return rank.equals(new Rank(2));
    }

    public boolean isRank7() {
        return rank.equals(new Rank(7));
    }
}
