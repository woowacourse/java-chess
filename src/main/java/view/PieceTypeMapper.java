package view;

import domain.Camp;
import domain.Piece;
import domain.PieceType;

import java.util.HashMap;

import java.util.Map;

public class PieceTypeMapper {

    private static final Map<PieceType, String> mapper = new HashMap<>();

    static {
        mapper.put(PieceType.ROOK, "r");
        mapper.put(PieceType.KNIGHT, "n");
        mapper.put(PieceType.BISHOP, "b");
        mapper.put(PieceType.QUEEN, "q");
        mapper.put(PieceType.KING, "k");
        mapper.put(PieceType.PAWN, "p");
    }

    public static String getTarget(Piece piece) {
        String target = mapper.getOrDefault(piece.getPieceType(), ".");
        if (piece.getCamp() != null && piece.getCamp().equals(Camp.BLACK)) {
            return target.toUpperCase();
        }
        return target;
    }
}
