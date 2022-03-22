package chess;

import java.util.List;
import java.util.stream.Collectors;

public class King {

    private static final List<Position> INIT_POSITIONS = List.of(new Position(0, 4), new Position(7, 3));

    private final Position position;

    private King(final Position position) {
        this.position = position;
    }

    public static List<King> init() {
        return INIT_POSITIONS.stream()
                .map(King::new)
                .collect(Collectors.toList());
    }

    public Position getPosition() {
        return position;
    }
}
