package chess.board;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class Coordinate {
    private final File file;
    private final Rank rank;

    private Coordinate(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Coordinate of(final File file, final Rank rank) {
        return CoordinateCache.cache.get(file.name() + rank.name());
    }

    private String key() {
        return this.file.name() + this.rank.name();
    }

    public Variation calculateVariation(final Coordinate coordinate) {
        return new Variation(coordinate.file.subtract(this.file), coordinate.rank.subtract(this.rank));
    }

    private static class CoordinateCache {
        private static final Map<String, Coordinate> cache;

        static {
            cache = Arrays.stream(File.values())
                    .flatMap(file -> Arrays
                            .stream(Rank.values())
                            .map(rank -> new Coordinate(file, rank))
                    ).collect(Collectors.toMap(Coordinate::key, coordinate -> coordinate));
        }
    }
}
