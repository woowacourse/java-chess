package chess.domain.board.position;

import java.util.Objects;

public class Position {

    private static final int GCD_LIMIT_MINIMUM = 0;

    private final File file;
    private final Rank rank;

    public Position(final int file, final int rank) {
        this.file = File.from(file);
        this.rank = Rank.from(rank);
    }

    public Position(final char file, final char rank) {
        this.file = File.from(file);
        this.rank = Rank.from(rank);
    }

    public Movement convertMovement(Position from) {

        int rankGap = calculateRankBetween(from);
        int fileGap = calculateFileBetween(from);

        int gcd = calculateGCD(Math.max(rankGap, fileGap), Math.min(rankGap, fileGap));

        return Movement.of(fileGap / gcd, rankGap / gcd);
    }

    private int calculateGCD(int max, int min) {
        if (min == GCD_LIMIT_MINIMUM) {
            return Math.abs(max);
        }

        return calculateGCD(min, max % min);
    }

    public Position moveBy(Movement movement) {
        return movement.nextPosition(file, rank);
    }

    public int calculateRankBetween(final Position from) {
        return rank.differenceBetween(from.rank);
    }

    public int calculateFileBetween(final Position from) {
        return file.differenceBetween(from.file);
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
