package chess.domain.location;

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

    private final File file;
    private final Rank rank;

    private Location(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public static Location of(File file, Rank rank) {
        return CACHE.get(toKey(file, rank));
    }

    public static Location of(String location) {
        checkLocationLength(location);
        String file = location.substring(0, 1);
        String rank = location.substring(1);
        return CACHE.get(toKey(File.of(file), Rank.of(rank)));
    }

    private static void checkLocationLength(String location) {
        if (location.length() != 2) {
            throw new IllegalArgumentException("[ERROR] 정확한 위치를 입력하세요.");
        }
    }

    public LocationDiff computeDiff(Location target) {
        int diffFile = file.computeDiff(target.file);
        int diffRank = rank.computeDiff(target.rank);

        return new LocationDiff(diffFile, diffRank);
    }

    public Location add(Direction direction) {
        return Location.of(file.add(direction.getX()), rank.add(direction.getY()));
    }

    public Location copyOf() {
        return Location.of(file, rank);
    }

    @Override
    public String toString() {
        return file.getValue() + rank.getValue();
    }
}
