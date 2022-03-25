package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Position {

    private static final Map<String, Position> cache;

    static {
        cache = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Position(rank, file))
                        .collect(Collectors.toList())
                        .stream())
                .collect(Collectors.toMap(
                        Position::getValue,
                        Function.identity()));
    }

    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position from(final String value) {
        if (!cache.containsKey(value)) {
            throw new IllegalArgumentException("유효하지 않은 위치입니다.");
        }
        return cache.get(value);
    }

    public static Position of(final Rank rank, final File file) {
        final String key = rank.getValue() + file.getValue();
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

        return Position.of(nextRank, nextFile);
    }

    public String getValue() {
        return rank.getValue() + file.getValue();
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }
}
