package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Square;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    private final static Map<String, King> CACHE = new HashMap<>();

    static {
        Stream.of(Color.values())
                .forEach(King::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = "K";
        if (color == Color.WHITE) {
            name = "k";
        }
        CACHE.putIfAbsent(color.getName(), new King(color, name, 0));
    }

    public static King of(Color color) {
        validateInput(color);
        return CACHE.get(color.getName());
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private King(Color color, String name, double score) {
        super(color, null, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        int index = -1;
        for (int i = 0; i < 2; i++) {
            availableSquares.add(addIfInBoundary(square, index, 0));
            availableSquares.add(addIfInBoundary(square, 0, index));
            availableSquares.add(addIfInBoundary(square, index * -1, index));
            availableSquares.add(addIfInBoundary(square, index, index));
            index *= -1;
        }
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        return calculateScope(square).stream()
                .filter(s -> !(board.containsKey(s) && board.get(s).getColor() == color))
                .collect(Collectors.toSet());
    }
}
