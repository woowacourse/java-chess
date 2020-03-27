package chess.domain.piece;

import chess.domain.board.BoardSquare;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.KNIGHT;

    static {
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
    public Set<BoardSquare> getAllCheatSheet(BoardSquare boardSquare) {
        NullChecker.validateNotNull(boardSquare);
        Set<BoardSquare> availableBoardSquares = new HashSet<>();
        int x = -1;
        int y = 2;
        for (int i = 0; i < 2; i++) {
            availableBoardSquares.add(boardSquare.addIfInBoundary(x, y));
            availableBoardSquares.add(boardSquare.addIfInBoundary(y, x));
            availableBoardSquares.add(boardSquare.addIfInBoundary(x, (-1) * y));
            availableBoardSquares.add(boardSquare.addIfInBoundary(y * -1, x));
            x *= -1;
            y *= -1;
        }
        return availableBoardSquares;
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        NullChecker.validateNotNull(boardSquare, board);
        return getAllCheatSheet(boardSquare).stream()
                .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
                .collect(Collectors.toSet());
    }
}
