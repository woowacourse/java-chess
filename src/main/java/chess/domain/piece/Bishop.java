package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bishop extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.BISHOP;
        for (Color color : Color.values()) {
            CACHE.put(color, new Bishop(color, type));
        }
    }

    public Bishop(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}
