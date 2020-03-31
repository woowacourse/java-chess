package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends Piece {
    private final static Map<Color, Knight> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "N";
    private final static String NAME_WHITE = "n";
    private final static double SCORE = 2.5;

    static {
        Stream.of(Color.values())
                .forEach(Knight::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        CACHE.putIfAbsent(color, new Knight(color, name, SCORE));
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
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        return Direction.knightDirection()
                .stream()
                .map(square::movedSquareInBoundary)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        return calculateScope(square).stream()
                .filter(s -> !(board.containsKey(s) && board.get(s).color == color))
                .collect(Collectors.toSet());
    }
}
