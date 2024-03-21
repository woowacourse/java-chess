package domain.position;

import domain.game.Direction;
import domain.game.Gap;
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
        int dFile = direction.getFileUnit();
        int dRank = direction.getRankUnit();
        file = file.add(dFile);
        rank = rank.add(dRank);
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

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Gap subtract(Position target) {
        return new Gap(file.subtract(target.getFile()), rank.subtract(target.getRank()));
    }

}
