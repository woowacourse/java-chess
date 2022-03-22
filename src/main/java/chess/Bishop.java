package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 2),
            new Position(0, 5),
            new Position(7, 2),
            new Position(7, 5)
    );

    private final Position position;

    public Bishop(final Position position) {
        this.position = position;
    }

    public static List<Bishop> init() {
        return INIT_POSITIONS.stream()
                .map(Bishop::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
