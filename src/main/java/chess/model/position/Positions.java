package chess.model.position;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Positions {

    private static final Map<File, Ranks> cache = new EnumMap<>(File.class);

    static {
        for (File file : File.values()) {
            cache.put(file, Ranks.create());
            createPosition(file);
        }
    }

    private static void createPosition(final File file) {
        for (Rank rank : Rank.values()) {
            final Ranks ranks = cache.get(file);
            ranks.put(rank, new Position(file, rank));
        }
    }

    public static List<Position> all() {
        return cache.values().stream()
                .flatMap(ranks -> ranks.getPositionAll().stream())
                .collect(toList());
    }

    public static List<Position> getPositionsBy(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> getInstance(file, rank))
                .collect(toUnmodifiableList());
    }

    public static Position getInstance(final File file, final Rank rank) {
        final Ranks ranks = cache.get(file);
        return ranks.getPosition(rank);
    }
}
