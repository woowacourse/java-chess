package chess.domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final int file, final int rank) {
        this(File.from(file), Rank.from(rank));
    }

    public Movement convertMovement(final Position from) {
        int rankGap = rank.calculateRankGap(from.rank);
        int fileGap = file.calculateFileGap(from.file);

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

    public Position moveBy(final Movement movement) {
        return movement.move(file, rank);
    }

    public int rankDifference(final Position other) {
        return Math.abs(this.rank.value() - other.rank.value());
    }

    public int fileDifference(final Position from) {
        return Math.abs(this.file.value() - from.file.value());
    }

    public boolean isEqualRank(final Rank rank) {
        return rank == this.rank;
    }

    public boolean isEqualFile(final File file) {
        return file == this.file;
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
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
}
