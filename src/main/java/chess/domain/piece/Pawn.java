package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of(1, 0),
            Position.of(1, 1), Position.of(1, 2), Position.of(1, 3),
            Position.of(1, 4), Position.of(1, 5), Position.of(1, 6),
            Position.of(1, 7));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of(6, 0),
            Position.of(6, 1), Position.of(6, 2), Position.of(6, 3),
            Position.of(6, 4), Position.of(6, 5), Position.of(6, 6),
            Position.of(6, 7));

    public Pawn(Position position, String name) {
        super(position, name);
    }

    public static List<Pawn> generate() {
        List<Pawn> blackPawns = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Pawn(position, "P"))
                .collect(Collectors.toList());
        List<Pawn> whitePawns = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Pawn(position, "p"))
                .collect(Collectors.toList());
        blackPawns.addAll(whitePawns);
        return blackPawns;
    }
}
