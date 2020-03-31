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

    private King(Color color, String name, double score) {
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        return Direction.everyDirection()
                .stream()
                .map(square::movedSquareInBoundary)
                .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                .collect(Collectors.toSet());
    }

    private boolean isNotSameSquareItself(Square square, Square movedSquare) {
        return movedSquare != square;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        return calculateScope(square).stream()
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
