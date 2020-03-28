package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Square;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends Piece {
    private final static Map<Color, Knight> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "N";
    private final static String NAME_WHITE = "n";

    static {
        Stream.of(Color.values())
                .forEach(Knight::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        CACHE.putIfAbsent(color, new Knight(color, name, 0));
    }

    public static Knight of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Knight(Color color, String name, double score) {
        super(color, null, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();

        int x = -1;
        int y = 2;
        for (int i = 0; i < 2; i++) {
            availableSquares.add(addIfInBoundary(square, x, y));
            availableSquares.add(addIfInBoundary(square, y, x));
            availableSquares.add(addIfInBoundary(square, x, (-1) * y));
            availableSquares.add(addIfInBoundary(square, y * -1, x));
            x *= -1;
            y *= -1;
        }
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        return calculateScope(square).stream()
                .filter(s -> !(board.containsKey(s) && board.get(s).color == color))
                .collect(Collectors.toSet());
    }
}
