package chess;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final int file, final int rank) {
        this.file = File.from(file);
        this.rank = Rank.from(rank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    public Movement convertMovement(Position from) {
        int rankGap = rank.value() - from.rank.value();
        int fileGap = file.value() - from.file.value();

        int greatestCommonDivisor = findGreatestCommonDivisor(Math.max(rankGap, fileGap), Math.min(rankGap, fileGap));

        return Movement.of(fileGap / greatestCommonDivisor,
                rankGap / greatestCommonDivisor);
    }

    private int findGreatestCommonDivisor(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        } else {
            return findGreatestCommonDivisor(num2, num1 % num2);
        }
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
