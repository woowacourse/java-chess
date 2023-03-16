package chess.domain.board;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {

    private static final int STRAIGHT_GAP = 0;

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public int calculateFileGap(final Position target) {
        return file.calculateGap(target.file);
    }

    public int calculateRankGap(final Position target) {
        return rank.calculateGap(target.rank);
    }

    public List<Position> between(final Position other) {
        final List<Rank> ranks = rank.between(other.rank);
        final List<File> files = file.between(other.file);

        if (calculateFileGap(other) == STRAIGHT_GAP || calculateRankGap(other) == STRAIGHT_GAP) {
            return betweenStraight(ranks, files);
        }
        if (ranks.size() != files.size()) {
            return Collections.emptyList();
        }
        return betweenDiagonal(ranks, files);
    }

    private List<Position> betweenStraight(final List<Rank> ranks, final List<File> files) {
        if (files.isEmpty()) {
            return betweenRankStraight(ranks);
        }
        return betweenFileStraight(files);
    }

    private List<Position> betweenRankStraight(final List<Rank> ranks) {
        return ranks.stream()
                .map(rank -> Position.of(file, rank))
                .collect(toList());
    }

    private List<Position> betweenFileStraight(final List<File> files) {
        return files.stream()
                .map(file -> Position.of(file, rank))
                .collect(toList());
    }

    private List<Position> betweenDiagonal(final List<Rank> ranks, final List<File> files) {
        return IntStream.range(0, ranks.size())
                .mapToObj(index -> Position.of(files.get(index), ranks.get(index)))
                .collect(toList());
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
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

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
