package techcourse.fp.chess.domain;

import java.util.Objects;

public final class Position {

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public static Position createByName(final String fileName, final int rank) {
        return new Position(File.createByName(fileName), Rank.of(rank));
    }

    public static Position from(final String rawValue) {
        final File file = File.createByName(String.valueOf(rawValue.charAt(0)));
        final Rank rank = Rank.of(Integer.parseInt(String.valueOf(rawValue.charAt(1))));
        return new Position(file, rank);
    }

    public int getFileOrder() {
        return file.getOrder();
    }

    public int getRankOrder() {
        return rank.getOrder();
    }

    public int getGapOfFileOrder(Position other) {
        return this.getFileOrder() - other.getFileOrder();
    }

    public int getGapOfRankOrder(Position other) {
        return this.getRankOrder() - other.getRankOrder();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Position otherPosition = (Position) other;
        return file == otherPosition.file && rank == otherPosition.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
