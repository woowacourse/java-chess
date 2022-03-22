package chess;

import java.util.List;
import java.util.stream.Collectors;

public class King extends AbstractPiece {

    private static final List<Position> INIT_POSITIONS = List.of(new Position(0, 4), new Position(7, 3));

    private King(final Position position) {
        super(position);
    }

    public static List<King> createKings() {
        return INIT_POSITIONS.stream()
                .map(King::new)
                .collect(Collectors.toList());
    }
}
