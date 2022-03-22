package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Rook extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(
            new Position(0, 0),
            new Position(0, 7),
            new Position(7, 7),
            new Position(7, 0)
    );

    private Rook(final Position position) {
        super(position);
    }

    public static List<Rook> createRooks() {
        return INIT_POSITIONS.stream()
                .map(Rook::new)
                .collect(Collectors.toList());
    }
}
