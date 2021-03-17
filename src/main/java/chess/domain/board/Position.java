package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Position {
    private static final List<Position> CACHE;

    static {
        CACHE = Arrays.stream(Vertical.values())
                .flatMap(vertical -> Arrays.stream(Horizontal.values())
                        .map(horizontal -> new Position(horizontal, vertical)))
                .collect(Collectors.toList());
    }

    private final Horizontal horizontal;
    private final Vertical vertical;

    private Position(Horizontal horizontal, Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Position of(Horizontal horizontal, Vertical vertical) {
        return CACHE.stream()
                .filter(position -> horizontal.equals(position.horizontal)
                        && vertical.equals(position.vertical))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public static List<Position> getPositions() {
        return Collections.unmodifiableList(CACHE);
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
        return vertical;
    }
}