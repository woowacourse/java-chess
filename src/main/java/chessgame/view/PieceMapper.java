package chessgame.view;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PieceMapper {

    private static final Map<Class<? extends Piece>, String> mapper = new HashMap<>();

    static {
        mapper.put(Rook.class, "r");
        mapper.put(Knight.class, "n");
        mapper.put(Bishop.class, "b");
        mapper.put(Queen.class, "q");
        mapper.put(King.class, "k");
        mapper.put(Pawn.class, "p");
    }

    public static String getTarget(final Piece piece) {
        String message = mapper.keySet()
                               .stream()
                               .filter(pieceType -> pieceType.isInstance(piece))
                               .map(mapper::get)
                               .findAny()
                               .orElse(".");
        return makeUpperCaseIfCampIsBlack(piece, message);
    }

    private static String makeUpperCaseIfCampIsBlack(final Piece piece, final String message) {
        if (piece.isEmpty() && piece.isSameCamp(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
