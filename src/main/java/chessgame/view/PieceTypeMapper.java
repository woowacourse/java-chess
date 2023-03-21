package chessgame.view;

import chessgame.domain.piece.*;
import chessgame.domain.square.Camp;
import chessgame.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class PieceTypeMapper {

    private static final Map<Class<? extends Piece>, String> mapper = new HashMap<>();

    static {
        mapper.put(Rook.class, "r");
        mapper.put(Knight.class, "n");
        mapper.put(Bishop.class, "b");
        mapper.put(Queen.class, "q");
        mapper.put(King.class, "k");
        mapper.put(Pawn.class, "p");
    }

    public static String getTarget(Square square) {
        String message = mapper.keySet()
                               .stream()
                               .filter(pieceType -> pieceType.isInstance(square.getPieceType()))
                               .map(mapper::get)
                               .findAny()
                               .orElse(".");
        return makeUpperCaseIfCampIsBlack(square, message);
    }

    private static String makeUpperCaseIfCampIsBlack(Square square, String message) {
        if (square.isExist() && square.isSameCamp(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
