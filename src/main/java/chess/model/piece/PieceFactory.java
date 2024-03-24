package chess.model.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PieceFactory {
    private static final Map<Integer, Piece> PIECES = new HashMap<>();

    static {
        for (Type type : Type.values()) {
            PIECES.put(convertToKey(Color.WHITE, type),new Piece(Color.WHITE, type));
            PIECES.put(convertToKey(Color.BLACK, type),new Piece(Color.BLACK, type));
        }
        PIECES.put(convertToKey(Color.WHITE, Type.PAWN), Pawn.from(Color.WHITE));
        PIECES.put(convertToKey(Color.BLACK, Type.PAWN), Pawn.from(Color.BLACK));
        PIECES.put(convertToKey(Color.NONE, Type.NONE), new Piece(Color.NONE, Type.NONE));
    }

    private static int convertToKey(Color color, Type type) {
        return Objects.hash(color, type);
    }
    public static Piece of(Color color, Type type) {
        return PIECES.get(convertToKey(color, type));
    }
}
