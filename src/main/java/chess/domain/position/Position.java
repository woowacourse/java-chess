package chess.domain.position;

import chess.domain.movement.UnitMovement;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean canMove(UnitMovement unitMovement) {
        return file.canMove(unitMovement.getFileDiff()) && rank.canMove(unitMovement.getRankDiff());
    }

    public Position move(UnitMovement unitMovement) {
        return new Position(file.move(unitMovement.getFileDiff()), rank.move(unitMovement.getRankDiff()));
    }

    public boolean isRank(Rank rank) {
        return this.rank == rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (file != position.file) return false;
        return rank == position.rank;
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
