package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Queen {

    private static final List<Position> INIT_POSITIONS = List.of(new Position(0, 3), new Position(7, 4));

    private final Position position;

    public Queen(final Position position) {
        this.position = position;
    }

    public static List<Queen> init() {
        return INIT_POSITIONS.stream()
                .map(Queen::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
