package chess.domain.piece;

import chess.domain.board.BoardSquare;
import util.NullChecker;

import java.util.HashMap;
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
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        return getAllCheatSheet(boardSquare).stream()
                .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
                .collect(Collectors.toSet());
    }
}
