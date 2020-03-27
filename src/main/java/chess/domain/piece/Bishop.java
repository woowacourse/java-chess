package chess.domain.piece;

import chess.domain.piece.abstraction.RepeatMovePiece;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Bishop extends RepeatMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.BISHOP;

    static {
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
