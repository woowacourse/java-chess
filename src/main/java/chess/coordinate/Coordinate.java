package chess.coordinate;

import java.util.Arrays;
import java.util.Map;
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
        return CoordinateCache.cache.get(file.getSymbol() + rank.getValue());
    }

    public static Coordinate of(String key) {
        return CoordinateCache.cache.get(key);
    }

    public Coordinate move(Direction direction) {
        File nextFile = file.sum(direction.getFile());
        Rank nextRank = rank.sum(direction.getRank());
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
}
