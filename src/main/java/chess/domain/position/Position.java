package chess.domain.position;

import chess.domain.movement.UnitMovement;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean canMove(UnitMovement unitMovement) {
        return rank.canMove(unitMovement.getRankDiff()) && file.canMove(unitMovement.getFileDiff());
    }

    public Position move(UnitMovement unitMovement) {
        return new Position(rank.move(unitMovement.getRankDiff()), file.move(unitMovement.getFileDiff()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (rank != position.rank) return false;
        return file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
