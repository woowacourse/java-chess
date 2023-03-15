package view;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piecetype.Bishop;
import domain.piecetype.King;
import domain.piecetype.Knight;
import domain.piecetype.Pawn;
import domain.piecetype.PieceType;
import domain.piecetype.Queen;
import domain.piecetype.Rook;

import java.util.HashMap;

import java.util.Map;

public class PieceTypeMapper {

    private static final Map<Class<? extends PieceType>, String> mapper = new HashMap<>();

    static {
        mapper.put(Rook.class, "r");
        mapper.put(Knight.class, "n");
        mapper.put(Bishop.class, "b");
        mapper.put(Queen.class, "q");
        mapper.put(King.class, "k");
        mapper.put(Pawn.class, "p");
    }

    public static String getTarget(Piece piece) {
        String message = mapper.keySet().stream()
                .filter(pieceType -> pieceType.isInstance(piece.getPieceType()))
                .map(mapper::get)
                .findAny()
                .orElseGet(() -> ".");
        return makeUpperCaseIfCampIsBlack(piece, message);
    }

    private static String makeUpperCaseIfCampIsBlack(Piece piece, String message) {
        if (piece.getCamp() != null && piece.getCamp().equals(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
