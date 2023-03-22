package chess.domain.position;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Position {

    private static final int STRAIGHT_GAP = 0;
    private static final Map<String, Position> CACHE;

    static {
        CACHE = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Map.entry(file.command() + rank.command(), new Position(file, rank))))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE.get(file.command() + rank.command());
    }

    public static Position from(final String position) {
        if (!CACHE.containsKey(position.toUpperCase())) {
            throw new IllegalArgumentException("잘못된 위치값입니다.");
        }
        return CACHE.get(position.toUpperCase());
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

    public int calculateFileGap(final Position target) {
        return file.calculateGap(target.file);
    }

    public int calculateRankGap(final Position target) {
        return rank.calculateGap(target.rank);
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
}
