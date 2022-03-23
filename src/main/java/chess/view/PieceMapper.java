package chess.view;

import chess.domain.Color;
import chess.domain.PieceConvertor;
import chess.domain.piece.*;
import java.util.HashMap;
import java.util.Map;

public class PieceMapper {

    private static final String BLACK = "RNBQKBNRP";
    private static final String WHITE = "rnbqkbnrp";

    private static final Map<Piece, String> PIECE_MAPPER = new HashMap<>();

    static {
        convert(Color.BLACK, BLACK);
        convert(Color.WHITE, WHITE);
    }

    private static void convert(Color color, String pieceString) {
        for (String message : pieceString.split("")) {
            PIECE_MAPPER.put(PieceConvertor.of(message, color), message);
        }
    }

    public static String getByPiece(Piece piece) {
        return PIECE_MAPPER.getOrDefault(piece, ".");
    }
}
