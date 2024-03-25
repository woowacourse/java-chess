package chess.domain.board;

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

    public boolean isOnSameDiagonalAs(Position targetPosition) {
        return (Math.abs(file.subtract(targetPosition.file)) == Math.abs(rank.subtract(targetPosition.rank)))
            && !this.equals(targetPosition);
    }

    public boolean isOnSameFileAs(Position targetPosition) {
        return file.equals(targetPosition.file);
    }

    public boolean isOnSameRankAs(Position targetPosition) {
        return rank.equals(targetPosition.rank);
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
