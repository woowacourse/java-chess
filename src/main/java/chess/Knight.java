package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Knight {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 1),
            new Position(0, 6),
            new Position(7, 6),
            new Position(7, 1)
    );

    private final Position position;

    public Knight(final Position position) {
        this.position = position;
    }

    public static List<Knight> init() {
        return INIT_POSITIONS.stream()
                .map(Knight::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
