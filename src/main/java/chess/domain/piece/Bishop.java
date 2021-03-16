package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of(0, 2),
            Position.of(0, 5));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of(7, 2),
            Position.of(7, 5));

    public Bishop(Position position, String name) {
        super(position, name);
    }

    public static List<Bishop> generate() {
        List<Bishop> blackBishops = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Bishop(position, "B"))
                .collect(Collectors.toList());
        List<Bishop> whiteBishops = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Bishop(position, "b"))
                .collect(Collectors.toList());
        blackBishops.addAll(whiteBishops);
        return blackBishops;
    }
}
