package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.model.Position;
import chess.model.piece.Piece;

public class EmblemMapper {
    private static final Map<String, String> emblemToFullNameTable = new HashMap<>();

    static {
        emblemToFullNameTable.put("p", "whitePawn");
        emblemToFullNameTable.put("r", "whiteRook");
        emblemToFullNameTable.put("n", "whiteKnight");
        emblemToFullNameTable.put("b", "whiteBishop");
        emblemToFullNameTable.put("q", "whiteQueen");
        emblemToFullNameTable.put("k", "whiteKing");

        emblemToFullNameTable.put("P", "blackPawn");
        emblemToFullNameTable.put("R", "blackRook");
        emblemToFullNameTable.put("N", "blackKnight");
        emblemToFullNameTable.put("B", "blackBishop");
        emblemToFullNameTable.put("Q", "blackQueen");
        emblemToFullNameTable.put("K", "blackKing");
    }

    public static String fullNameFrom(Piece piece) {
        if (Objects.isNull(piece)) {
            return "empty";
        }

        return emblemToFullNameTable.get(piece.getEmblem());
    }
}
