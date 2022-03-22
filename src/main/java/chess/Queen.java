package chess;

import java.util.List;
import java.util.stream.Collectors;

public class Queen extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(new Position(0, 3), new Position(7, 4));

    private Queen(final Position position) {
        super(position);
    }

    public static List<Queen> createQueens() {
        return INIT_POSITIONS.stream()
                .map(Queen::new)
                .collect(Collectors.toList());
    }
}
