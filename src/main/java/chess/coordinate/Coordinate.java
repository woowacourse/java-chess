package chess.coordinate;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Coordinate implements Comparable<Coordinate> {
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

    @Override
    public int compareTo(Coordinate o) {
        int file = this.file.getValue();
        int rank = this.rank.getValue();
        int otherFile = o.file.getValue();
        int otherRank = o.rank.getValue();

        int rankCompare = Integer.compare(otherRank, rank);
        if (rankCompare == 0) {
            return Integer.compare(file, otherFile);
        }
        return rankCompare;
    }

    public String getRawKey() {
        return this.file.getSymbol() + this.rank.getValue();
    }

    public Rank getRank() {
        return rank;
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
}
