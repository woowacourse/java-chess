package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Location {
    private static final List<Location> CACHE;

    static {
        CACHE = Arrays.stream(Horizontal.values())
                .flatMap(horizontal -> Arrays.stream(Vertical.values())
                        .map(vertical -> new Location(horizontal, vertical)))
                .collect(Collectors.toList());
    }

    private final Horizontal horizontal;
    private final Vertical vertical;

    private Location(Horizontal horizontal, Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Location of(String horizontal, String vertical) {
        return CACHE.stream()
                .filter(location -> Horizontal.find(horizontal).equals(location.horizontal)
                        && Vertical.find(vertical).equals(location.vertical))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }


}