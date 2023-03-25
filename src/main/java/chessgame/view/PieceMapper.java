package chessgame.view;

import chessgame.domain.piece.Camp;
import chessgame.domain.piecetype.*;

import java.util.HashMap;
import java.util.Map;

public class PieceMapper {

    private static final Map<Class<? extends PieceType>, String> mapper = new HashMap<>();

    static {
        mapper.put(Rook.class, "r");
        mapper.put(Knight.class, "n");
        mapper.put(Bishop.class, "b");
        mapper.put(Queen.class, "q");
        mapper.put(King.class, "k");
        mapper.put(Pawn.class, "p");
    }

    public static String getTarget(final PieceType piece) {
        String message = mapper.keySet()
                               .stream()
                               .filter(pieceType -> pieceType.isInstance(piece))
                               .map(mapper::get)
                               .findAny()
                               .orElse(".");
        return makeUpperCaseIfCampIsBlack(piece, message);
    }

    private static String makeUpperCaseIfCampIsBlack(final PieceType piece, final String message) {
        if (piece.isEmpty() && piece.isSameCamp(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
