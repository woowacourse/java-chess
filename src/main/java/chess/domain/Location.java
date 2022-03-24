package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private static final Map<String, Location> CACHE = new HashMap<>();

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

    public static Location of(String location) {
        String file = location.substring(0, 1);
        String rank = location.substring(1);
        return CACHE.get(toKey(File.of(file), Rank.of(rank)));
    }

    public LocationDiff computeDiff(Location origin) {
        int diffFile = file.computeDiff(origin.file);
        int diffRank = rank.computeDiff(origin.rank);

        return new LocationDiff(diffFile, diffRank);
    }
}
