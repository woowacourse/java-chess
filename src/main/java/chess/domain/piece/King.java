package chess.domain.piece;

import chess.domain.piece.abstraction.OneTimeMovePiece;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class King extends OneTimeMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.KING;

    static {
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

}
