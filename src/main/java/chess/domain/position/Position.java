package chess.domain.position;

import chess.domain.square.piece.movement.UnitMovement;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public int calculateRankDistance(Position position) {
        return rank.calculateDistance(position.rank);
    }

    public int subtractRank(Position position) {
        return rank.subtract(position.rank);
    }

    public int calculateFileDistance(Position position) {
        return file.calculateDistance(position.file);
    }

    public int subtractFile(Position position) {
        return file.subtract(position.file);
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

    public int getRankValue() {
        return rank.getValue();
    }

    public int getFileValue() {
        return file.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
