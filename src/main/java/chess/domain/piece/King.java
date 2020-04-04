package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    private final static Map<Color, King> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "K";
    private final static String NAME_WHITE = "k";
    private final static double SCORE = 0;

    private King(Color color, String name, double score) {
        super(color, name, score);
    }

    static {
        Stream.of(Color.values())
                .forEach(King::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new King(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
    }

    public static King of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        return Direction.everyDirection()
                .stream()
                .map(direction -> square.movedSquareInBoundary(direction, 1))
                .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Square> calculateMovableSquares(Square currentSquare, Map<Square, Piece> board) {
        validateNotNull(currentSquare, board);
        return calculateScope(currentSquare).stream()
                .filter(s -> isAvailableSquare(board, s))
                .collect(Collectors.toSet());
    }

    private boolean isAvailableSquare(Map<Square, Piece> board, Square square) {
        if (!board.containsKey(square)) {
            return true;
        }
        Piece piece = board.get(square);
        return piece.color != color;
    }
}
