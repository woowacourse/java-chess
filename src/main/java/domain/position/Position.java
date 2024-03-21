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

    public Position(final Position position) {
        this.file = position.getFile();
        this.rank = position.getRank();
    }

    public void move(final Direction direction) {
        int dFile = direction.getFileStepSize();
        int dRank = direction.getRankStepSize();
        file = file.add(dFile);
        rank = rank.add(dRank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public DirectionVector subtract(Position target) {
        return new DirectionVector(file.subtract(target.getFile()), rank.subtract(target.getRank()));
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
