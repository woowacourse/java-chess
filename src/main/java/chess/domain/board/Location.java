package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Location {
    private static final List<Location> CACHE;

    static {
        CACHE = Arrays.stream(Vertical.values())
                .flatMap(vertical -> Arrays.stream(Horizontal.values())
                        .map(horizontal -> new Location(horizontal, vertical)))
                .collect(Collectors.toList());
    }

    private final Horizontal horizontal;
    private final Vertical vertical;

    private Location(Horizontal horizontal, Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Location of(Horizontal horizontal, Vertical vertical) {
        return CACHE.stream()
                .filter(location -> horizontal.equals(location.horizontal)
                        && vertical.equals(location.vertical))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public static List<Location> getLocations() {
        return Collections.unmodifiableList(CACHE);
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
        return vertical;
    }
}