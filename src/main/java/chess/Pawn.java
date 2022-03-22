package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(1, 3),
            new Position(1, 4),
            new Position(1, 5),
            new Position(1, 6),
            new Position(1, 7),

            new Position(6, 0),
            new Position(6, 1),
            new Position(6, 2),
            new Position(6, 3),
            new Position(6, 4),
            new Position(6, 5),
            new Position(6, 6),
            new Position(6, 7)
    );

    private Pawn(final Position position) {
        super(position);
    }

    public static List<Pawn> createPawns() {
        return INIT_POSITIONS.stream()
                .map(Pawn::new)
                .collect(Collectors.toList());
    }
}
