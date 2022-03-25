package chess.domain.board;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position advancePosition(Direction direction) {
        return new Position(
                File.numberOf(file.getNumber() + direction.getX()),
                Rank.numberOf(rank.getNumber() + direction.getY())
        );
    }

    public int getXDistance(Position to) {
        return to.file.getNumber() - this.file.getNumber();
    }

    public int getYDistance(Position to) {
        return to.rank.getNumber() - this.rank.getNumber();
    }

    public boolean isEqualRank(Rank rank) {
        return this.rank == rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
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
