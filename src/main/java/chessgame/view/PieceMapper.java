package chessgame.view;

import chessgame.domain.piece.Camp;
import chessgame.domain.piece.Piece;
import chessgame.domain.piecetype.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public static String getTarget(Piece square) {
        Optional<PieceType> piece = square.getPiece();
        String message = mapper.keySet()
                               .stream()
                               .filter(pieceType ->
                                       piece.isPresent() && pieceType.isInstance(piece.get()))
                               .map(mapper::get)
                               .findAny()
                               .orElse(".");
        return makeUpperCaseIfCampIsBlack(square, message);
    }

    private static String makeUpperCaseIfCampIsBlack(Piece piece, String message) {
        if (piece.isExist() && piece.isSameCamp(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
