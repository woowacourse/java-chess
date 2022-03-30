package chess.domain.postion;

import chess.domain.Direction;

import java.util.Objects;

public class Position implements Comparable<Position> {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final int inputFile, final int inputRank) {
        final File file = File.from(inputFile);
        final Rank rank = Rank.from(inputRank);
        validatePosition(file, rank);

        return new Position(file, rank);
    }

    private static void validatePosition(final File file, final Rank rank) {
        if (file.equals(File.NOTHING) || rank.equals(Rank.NOTHING)) {
            throw new IllegalArgumentException("잘못된 위치 정보입니다.");
        }
    }

    public Position nextPositionBy(Direction direction) {
        File nextFile = File.from((file.getNumber() + direction.file()));
        Rank nextRank = Rank.from(rank.getNumber() + direction.rank());

        return new Position(nextFile, nextRank);
    }

    public boolean isSameRank(Position other) {
        return rank.equals(other.rank);
    }

    public boolean isSameFile(Position other) {
        return file.equals(other.file);
    }

    public boolean isDiagonal(Position other) {
        return (file.calculateAbsoluteValue(other.file)
                == rank.calculateAbsoluteValue(other.rank));
    }

    public boolean isInOneSquare(Position other) {
        int fileAbsoluteValue = file.calculateAbsoluteValue(other.file);
        int rankAbsoluteValue = rank.calculateAbsoluteValue(other.rank);

        if (fileAbsoluteValue + rankAbsoluteValue == 1) {
            return true;
        }

        return fileAbsoluteValue == 1 && rankAbsoluteValue == 1;
    }

    public boolean isTwoRankDifference(Position other) {
        int fileAbsoluteValue = file.calculateAbsoluteValue(other.file);
        int rankAbsoluteValue = rank.calculateAbsoluteValue(other.rank);

        return rankAbsoluteValue == 2 && fileAbsoluteValue == 0;
    }

    public boolean isInitPawnRank() {
        return rank.equals(Rank.TWO) || rank.equals(Rank.SEVEN);
    }

    public boolean isNothing() {
        return file.isNothing() || rank.isNothing();
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public int compareTo(final Position other) {
        if (rank == other.getRank()) {
            return file.calculateDifference(other.getFile());
        }

        return other.getRank().calculateDifference(rank);
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
