package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(1, 3),
            new Position(1, 4),
            new Position(1, 5),
            new Position(1, 6),

            new Position(6, 0),
            new Position(6, 1),
            new Position(6, 2),
            new Position(6, 3),
            new Position(6, 4),
            new Position(6, 5),
            new Position(6, 6)
    );

    private final Position position;

    public Pawn(final Position position) {
        this.position = position;
    }

    public static List<Pawn> init() {
        return INIT_POSITIONS.stream()
                .map(Pawn::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
