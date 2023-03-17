package chess;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final int file, final int rank) {
        this.file = File.from(file);
        this.rank = Rank.from(rank);
    }

    public Movement convertMovement(Position from) {
        // TODO: 둘 다 0인 경우는 예외 처리하자! Arit~~ 조심

        int rankGap = rank.value() - from.rank.value();
        int fileGap = file.value() - from.file.value();

        int greatestCommonDivisor = findGreatestCommonDivisor(Math.max(rankGap, fileGap), Math.min(rankGap, fileGap));

        return Movement.of(fileGap / greatestCommonDivisor,
                rankGap / greatestCommonDivisor);
    }

    private int findGreatestCommonDivisor(int num1, int num2) {
        if (num2 == 0) {
            return Math.abs(num1);
        }
        return findGreatestCommonDivisor(num2, num1 % num2);
    }

    public Position moveBy(Movement movement) {
        return movement.nextPosition(file, rank);
    }

    public boolean isSameRank(int rank) {
        return this.rank.isSame(rank);
    }

    public int rankDifference(final Position from) {
        return this.rank.value() - from.rank.value();
    }

    public int fileDifference(final Position from) {
        return this.file.value() - from.file.value();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }
}
