package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
}
