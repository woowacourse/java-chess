package chess.domain.piece;

import chess.domain.Position;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpectedInitialPieces {

    public static List<Position> whitePawnsPositions() {
        return Arrays.asList(Position.of(0, 1),
            Position.of(1, 1),
            Position.of(2, 1),
            Position.of(3, 1),
            Position.of(4, 1),
            Position.of(5, 1),
            Position.of(6, 1),
            Position.of(7, 1));
    }

    public static List<Position> blackPawnPositions() {
        return Arrays.asList(Position.of(0, 6),
            Position.of(1, 6),
            Position.of(2, 6),
            Position.of(3, 6),
            Position.of(4, 6),
            Position.of(5, 6),
            Position.of(6, 6),
            Position.of(7, 6));
    }

    public static List<Position> whiteBishopPositions() {
        return Arrays.asList(Position.of(2, 0), Position.of(5, 0));
    }

    public static List<Position> blackBishopPositions() {
        return Arrays.asList(Position.of(2, 7), Position.of(5, 7));
    }

    public static List<Position> whiteKnightPositions() {
        return Arrays.asList(Position.of(1, 0), Position.of(6, 0));
    }

    public static List<Position> blackKnightPositions() {
        return Arrays.asList(Position.of(1, 7), Position.of(6, 7));
    }

    public static List<Position> whiteRookPositions() {
        return Arrays.asList(Position.of(0, 0), Position.of(7, 0));
    }

    public static List<Position> blackRookPositions() {
        return Arrays.asList(Position.of(0, 7), Position.of(7, 7));
    }

    public static List<Position> whiteQueenPositions() {
        return Collections.singletonList(Position.of(3, 0));
    }

    public static List<Position> blackQueenPositions() {
        return Collections.singletonList(Position.of(3, 7));
    }

    public static List<Position> whiteKingPositions() {
        return Collections.singletonList(Position.of(4, 0));
    }

    public static List<Position> blackKingPositions() {
        return Collections.singletonList(Position.of(4, 7));
    }
}
