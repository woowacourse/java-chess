package chess.domain;

import chess.domain.board.Location;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveLocation {
    private final Location source;
    private final Location target;

    private MoveLocation(Location source, Location target) {
        this.source = source;
        this.target = target;
    }

    public static MoveLocation of(String text) {
        List<String> collect = toList(text);
        return new MoveLocation(Location.of(collect.get(1)), Location.of(collect.get(2)));
    }

    private static List<String> toList(String text) {
        return Arrays.stream(text.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }
}
