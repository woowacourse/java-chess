package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Position {

    private static final Map<String, Position> cache;

    static {
        cache = Arrays.stream(File.values())
                .flatMap(rank -> Arrays.stream(Rank.values())
                        .map(file -> new Position(rank, file))
                        .collect(Collectors.toList())
                        .stream())
                .collect(Collectors.toMap(
                        position -> position.file.value() + position.rank.value(),
                        Function.identity()));
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String value) {
        if (!cache.containsKey(value)) {
            throw new IllegalArgumentException("유효하지 않은 위치입니다.");
        }
        return cache.get(value);
    }

    public static Position of(final File file, final Rank rank) {
        final String key = file.value() + rank.value();
        return cache.get(key);
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

    public boolean isSameRank(final String targe) {
        return rank.equals(Rank.of(targe));
    }

    public boolean isSameFile(final Position target) {
        return file.equals(target.file);
    }

    public Direction findDirection(final Position target) {
        final int fileDistance = calculateFileGap(target);
        final int rankDistance = calculateRankGap(target);

        return Direction.of(fileDistance, rankDistance);
    }

    private int calculateRankGap(final Position target) {
        return toGap(rankDistance(target));
    }

    private int calculateFileGap(final Position target) {
        return toGap(fileDistance(target));
    }

    private int toGap(final int distance) {
        return Integer.compare(distance, 0);
    }

    public Position toNextPosition(final Direction direction) {
        final File nextFile = file.add(direction.fileGap());
        final Rank nextRank = rank.add(direction.rankGap());

        return Position.of(nextFile, nextRank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
