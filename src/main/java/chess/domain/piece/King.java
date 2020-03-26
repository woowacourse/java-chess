package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.KING;
        for (Color color : Color.values()) {
            CACHE.put(color, new King(color, type));
        }
    }

    public King(Color color, Type type) {
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
        int index = -1;
        for (int i = 0; i < 2; i++) {
            availableSquares.add(square.addIfInBoundary(index, 0));
            availableSquares.add(square.addIfInBoundary(0, index));
            availableSquares.add(square.addIfInBoundary(index * -1, index));
            availableSquares.add(square.addIfInBoundary(index, index));
            index *= -1;
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
