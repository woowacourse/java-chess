package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 2),
            new Position(0, 5),
            new Position(7, 2),
            new Position(7, 5)
    );

    private Bishop(final Position position) {
        super(position);
    }

    public static List<Bishop> createBishops() {
        return INIT_POSITIONS.stream()
                .map(Bishop::new)
                .collect(Collectors.toList());
    }
}
