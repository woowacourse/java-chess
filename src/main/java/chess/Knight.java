package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 1),
            new Position(0, 6),
            new Position(7, 6),
            new Position(7, 1)
    );

    private Knight(final Position position) {
        super(position);
    }

    public static List<Knight> createKnights() {
        return INIT_POSITIONS.stream()
                .map(Knight::new)
                .collect(Collectors.toList());
    }
}
