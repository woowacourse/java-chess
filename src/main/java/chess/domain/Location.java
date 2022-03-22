package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private static Map<String, Location> CACHE = new HashMap<>();

    static {
        Arrays.stream(File.values())
                .forEach(file -> Arrays.stream(Rank.values())
                        .forEach(rank -> CACHE.put(toKey(file, rank), new Location(file, rank))));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    private final File file;
    private final Rank rank;

    private Location(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Location of(File file, Rank rank) {
        return CACHE.get(toKey(file, rank));
    }
}
