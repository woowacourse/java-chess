package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of(0, 1),
            Position.of(0, 6));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of(7, 1),
            Position.of(7, 6));

    public Knight(Position position, String name) {
        super(position, name);
    }

    public static List<Knight> generate() {
        List<Knight> blackKnights = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Knight(position, "N"))
                .collect(Collectors.toList());
        List<Knight> whiteKnights = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Knight(position, "n"))
                .collect(Collectors.toList());
        blackKnights.addAll(whiteKnights);
        return blackKnights;
    }
}
