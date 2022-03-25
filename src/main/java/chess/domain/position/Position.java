package chess.domain.position;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(final String text) {
        this.rank = Rank.of(String.valueOf(text.charAt(0)));
        this.file = File.of(String.valueOf(text.charAt(1)));
    }

    public Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public int rankDistance(final Position target) {
        return rank.calculateDistance(target.rank);
    }

    public int fileDistance(final Position target) {
        return file.calculateDistance(target.file);
    }

    public boolean isSameRank(final Position target) {
        return rank.equals(target.rank);
    }

    public boolean isSameFile(final Position target) {
        return file.equals(target.file);
    }

    public boolean isSameFile(final String target) {
        return file.equals(File.of(target));
    }

    public Direction findDirection(final Position target) {
        final int rankDistance = calculateRankGap(target);
        final int fileDistance = calculateFileGap(target);

        return Direction.of(rankDistance, fileDistance);
    }

    private int calculateRankGap(final Position target) {
        return toGap(rankDistance(target));
    }

    private int calculateFileGap(final Position target) {
        return toGap(fileDistance(target));
    }

    private int toGap(final int distance) {
        if (distance > 0) {
            return 1;
        }

        if (distance < 0) {
            return -1;
        }
        return 0;
    }

    public Position toNextPosition(final Direction direction) {
        final Rank nextRank = rank.add(direction.rankGap());
        final File nextFile = file.add(direction.fileGap());

        return new Position(nextRank, nextFile);
    }

    public String getValue() {
        return rank.getValue() + file.getValue();
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
