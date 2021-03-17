package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of(0, 0),
            Position.of(0, 7));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of(7, 0),
            Position.of(7, 7));

    public Rook(Position position, String name) {
        super(position, name);
    }

    public static List<Rook> generate() {
        List<Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, "R"))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, "r"))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }
}
