package chess.model.domain.piece;

import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Rook extends RepeatMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        CACHE.put(Color.BLACK, new Rook(Color.BLACK, Type.ROOK));
        CACHE.put(Color.WHITE, new Rook(Color.WHITE, Type.ROOK));
    }

    public Rook(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}

