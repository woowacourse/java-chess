package chess.domain.piece;

import chess.domain.piece.abstraction.RepeatMovePiece;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Rook extends RepeatMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.ROOK;

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Rook(color, type));
        }
    }

    public Rook(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}

