package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Rook {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 0),
            new Position(0, 7),
            new Position(7, 7),
            new Position(7, 0)
    );

    private final Position position;

    public Rook(final Position position) {
        this.position = position;
    }

    public static List<Rook> init() {
        return INIT_POSITIONS.stream()
                .map(Rook::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
