package chess.view;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class ImageNameMapper {

    private static final Map<String, String> MAPPER = new HashMap<>();
    private static final String PAWN_LETTER = "PAWN";
    private static final String BISHOP_LETTER = "BISHOP";
    private static final String KNIGHT_LETTER = "KNIGHT";
    private static final String KING_LETTER = "KING";
    private static final String QUEEN_LETTER = "QUEEN";
    private static final String ROOK_LETTER = "ROOK";

    static {
        MAPPER.put(Pawn.class.getName(), PAWN_LETTER);
        MAPPER.put(Bishop.class.getName(), BISHOP_LETTER);
        MAPPER.put(Knight.class.getName(), KNIGHT_LETTER);
        MAPPER.put(King.class.getName(), KING_LETTER);
        MAPPER.put(Queen.class.getName(), QUEEN_LETTER);
        MAPPER.put(Rook.class.getName(), ROOK_LETTER);
    }

    public static String from(final Piece piece) {
        final String letter = MAPPER.get(piece.getClass().getName());
        if (piece.isSameColor(Color.WHITE)) {
            return letter + "_WHITE";
        }
        return letter + "_BLACK";
    }
}
