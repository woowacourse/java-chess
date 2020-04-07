package chess.domain.coordinate;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Coordinate {
    private final File file;
    private final Rank rank;

    private Coordinate(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Coordinate of(final File file, final Rank rank) {
        String key = file.getSymbol() + rank.getValue();
        return Coordinate.of(key);
    }

    public static Coordinate of(String key) {
        Coordinate coordinate = CoordinateCache.cache.get(key);
        if (Objects.isNull(coordinate)) {
            throw new IllegalArgumentException("Coordinate out of range.");
        }
        return coordinate;
    }

    public Coordinate move(Direction direction) {
        File nextFile = file.sum(direction.getFileVariation());
        Rank nextRank = rank.sum(direction.getRankVariation());
        return of(nextFile, nextRank);
    }

    private String key() {
        return this.file.getSymbol() + this.rank.getValue();
    }

    public Vector calculateVector(final Coordinate source) {
        return new Vector(this.file.subtract(source.file), this.rank.subtract(source.rank));
    }

    private static class CoordinateCache {
        private static final Map<String, Coordinate> cache;

        static {
            cache = Arrays.stream(File.values())
                    .flatMap(CoordinateCache::makeCoordinate)
                    .collect(Collectors.toMap(Coordinate::key, coordinate -> coordinate));
        }

        private static Stream<Coordinate> makeCoordinate(File file) {
            return Arrays.stream(Rank.values())
                    .map(rank -> new Coordinate(file, rank));
        }
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return file.getSymbol() + rank.getValue();
    }
}
