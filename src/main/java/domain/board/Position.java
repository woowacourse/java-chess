package domain.board;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        return new Position(File.valueOf(file), Rank.valueOf(rank));
    }

    public Position nextPosition(Direction direction) {
        return Position.of(file.value() + direction.getFileDirection(),
                rank.value() + direction.getRankDirection());
    }

    public boolean isWhitePawnInitialPosition() {
        return rank.isRankTwo();
    }

    public boolean isBlackPawnInitialPosition() {
        return rank.isRankSeven();
    }

    public int calculateFileDifference(Position otherPosition) {
        return file.subtract(otherPosition.file);
    }

    public int calculateRankDifference(Position otherPosition) {
        return rank.subtract(otherPosition.rank);
    }

    public boolean isOnSameDiagonalAs(Position target) {
        return Math.abs(file.subtract(target.file))
                == Math.abs(rank.subtract(target.rank));
    }

    public boolean isOnSameFileAs(Position target) {
        return file.equals(target.file);
    }

    public boolean isOnSameRankAs(Position target) {
        return rank.equals(target.rank);
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

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
