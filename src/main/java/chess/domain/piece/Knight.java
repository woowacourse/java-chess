package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.KNIGHT;
        for (Color color : Color.values()) {
            CACHE.put(color, new Knight(color, type));
        }
    }

    public Knight(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<Square> getAllCheatSheet(Square square) {
        NullChecker.validateNotNull(square);
        Set<Square> availableSquares = new HashSet<>();
        int x = -1;
        int y = 2;
        for (int i = 0; i < 2; i++) {
            availableSquares.add(square.addIfInBoundary(x, y));
            availableSquares.add(square.addIfInBoundary(y, x));
            availableSquares.add(square.addIfInBoundary(x, (-1) * y));
            availableSquares.add(square.addIfInBoundary(y * -1, x));
            x *= -1;
            y *= -1;
        }
        return availableSquares;
    }

    @Override
    public Set<Square> getCheatSheet(Square square, Map<Square, Piece> board) {
        NullChecker.validateNotNull(square, board);
        return getAllCheatSheet(square).stream()
                .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
                .collect(Collectors.toSet());
    }
}
