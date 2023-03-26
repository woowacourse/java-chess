package chess.domain.position;

import java.util.Objects;

public final class Position {

    private static final int NEAR_DISTANCE_EXCLUDE = 2;
    private static final int INDEX_ADJUST = 1;

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String position) {
        char c = position.charAt(0);
        char d = position.charAt(1);
        File from = File.from(c - 'A' + INDEX_ADJUST);
        Rank from1 = Rank.from(d - '0');

        return new Position(from, from1);
    }

    public boolean isFileEquals(final Position target) {
        return file.equals(target.file);
    }

    public boolean isFileEquals(final File file) {
        return this.file.equals(file);
    }

    public boolean isRankEquals(final Position target) {
        return rank.equals(target.rank);
    }

    public int computeFileDistance(final Position target) {
        return this.file.sub(target.file);
    }

    public int computeRankDistance(final Position target) {
        return this.rank.sub(target.rank);
    }

    public Position moveTo(int fileDistance, int rankDistance) {
        File fileMoved = file.moveTo(fileDistance);
        Rank rankMoved = rank.moveTo(rankDistance);

        return new Position(fileMoved, rankMoved);
    }

    public boolean isNear(final Position target) {
        return fileSubLessThan(target, NEAR_DISTANCE_EXCLUDE) && rankSubLessThan(target, NEAR_DISTANCE_EXCLUDE);
    }

    public boolean fileSubLessThan(final Position target, final int distance) {
        return Math.abs(this.file.sub(target.file)) < distance;
    }

    public boolean rankSubLessThan(final Position target, final int distance) {
        return Math.abs(this.rank.sub(target.rank)) < distance;
    }

    public boolean distanceEquals(final Position target, final int distance) {
        int fileDistance = this.rank.sub(target.rank);
        int rankDistance = this.file.sub(target.file);

        return fileDistance * fileDistance + rankDistance * rankDistance == distance;
    }

    public boolean rankNotEquals(final int rankExpected) {
        return this.rank.NotEquals(rankExpected);
    }

    public boolean rankSubEquals(final Position target, final int expected) {
        return this.rank.sub(target.rank) == expected;
    }

    public Rank getRank() {
        return rank;
    }

    public String getFile() {
        return file.name();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return file.toString() + rank.getValue();
    }
}
