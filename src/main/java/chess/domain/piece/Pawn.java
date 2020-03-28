package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private final static Map<String, Pawn> CACHE = new HashMap<>();

    static {
        Stream.of(Color.values())
                .forEach(Pawn::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = "P";
        if (color == Color.WHITE) {
            name = "p";
        }
        CACHE.putIfAbsent(color.getName(), new Pawn(color, name, 0));
    }

    public static Pawn of(Color color) {
        validateInput(color);
        return CACHE.get(color.getName());
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Pawn(Color color, String name, double score) {
        super(color, null, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        return null;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        return null;
    }
}
