package view;

import domain.square.Camp;
import domain.square.Square;
import domain.piece.sliding.Bishop;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.Pawn;
import domain.piece.Piece;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.util.HashMap;

import java.util.Map;

public final class PieceTypeMapper {

    private static final Map<Class<? extends Piece>, String> mapper = new HashMap<>();

    static {
        mapper.put(Rook.class, "r");
        mapper.put(Knight.class, "n");
        mapper.put(Bishop.class, "b");
        mapper.put(Queen.class, "q");
        mapper.put(King.class, "k");
        mapper.put(Pawn.class, "p");
    }

    public static String getTarget(final Square square) {
        String message = mapper.keySet().stream()
                .filter(pieceType -> pieceType.isInstance(square.getPieceType()))
                .map(mapper::get)
                .findAny()
                .orElseGet(() -> ".");
        return makeUpperCaseIfCampIsBlack(square, message);
    }

    private static String makeUpperCaseIfCampIsBlack(final Square square, final String message) {
        if (square.getCamp() != null && square.getCamp().equals(Camp.BLACK)) {
            return message.toUpperCase();
        }
        return message;
    }
}
