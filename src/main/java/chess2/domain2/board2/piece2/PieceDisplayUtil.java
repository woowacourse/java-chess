package chess2.domain2.board2.piece2;

import java.util.HashMap;
import java.util.Map;

public class PieceDisplayUtil {

    private PieceDisplayUtil() {
    }

    public static String toDisplay(Piece piece) {
        return PieceDisplayMap.displayOf(piece);
    }

    private static class PieceDisplayMap {

        static final String BLACK_DISPLAY_FORMATS = "♗♘♙♖♕♔";
        static final String WHITE_DISPLAY_FORMATS = "♝♞♟♜♛♚";
        static final String EMPTY_DISPLAY_FORMAT = ".";

        static final int PAWN_IDX = 0;
        static final int KNIGHT_IDX = 1;
        static final int BISHOP_IDX = 2;
        static final int ROOK_IDX = 3;
        static final int QUEEN_IDX = 4;
        static final int KING_IDX = 5;

        static final Map<Piece, String> displayMap = new HashMap<>();

        static {
            String[] blackDisplayFormats = BLACK_DISPLAY_FORMATS.split("");
            String[] whiteDisplayFormats = WHITE_DISPLAY_FORMATS.split("");

            initDisplayMap(blackDisplayFormats, Color.BLACK);
            initDisplayMap(whiteDisplayFormats, Color.WHITE);
        }

        static void initDisplayMap(String[] displayFormats, Color color) {
            displayMap.put(new Piece(color, PieceType.PAWN), displayFormats[PAWN_IDX]);
            displayMap.put(new Piece(color, PieceType.KNIGHT), displayFormats[KNIGHT_IDX]);
            displayMap.put(new Piece(color, PieceType.BISHOP), displayFormats[BISHOP_IDX]);
            displayMap.put(new Piece(color, PieceType.ROOK), displayFormats[ROOK_IDX]);
            displayMap.put(new Piece(color, PieceType.QUEEN), displayFormats[QUEEN_IDX]);
            displayMap.put(new Piece(color, PieceType.KING), displayFormats[KING_IDX]);
        }

        static String displayOf(Piece piece) {
            return displayMap.getOrDefault(piece, EMPTY_DISPLAY_FORMAT);
        }
    }
}
