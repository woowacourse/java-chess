package chess2.domain2.board2.piece2;

import java.util.HashMap;
import java.util.Map;

public class PieceDisplayUtil {

    private static final String BLACK_DISPLAY_FORMATS = "♗♘♙♖♕♔";
    private static final String WHITE_DISPLAY_FORMATS = "♝♞♟♜♛♚";
    private static final String EMPTY_DISPLAY_FORMAT = ".";

    private static final int PAWN_IDX = 0;
    private static final int KNIGHT_IDX = 1;
    private static final int BISHOP_IDX = 2;
    private static final int ROOK_IDX = 3;
    private static final int QUEEN_IDX = 4;
    private static final int KING_IDX = 5;

    private static final Map<Piece, String> displayMap = new HashMap<>();

    static {
        String[] blackDisplayFormats = BLACK_DISPLAY_FORMATS.split("");
        String[] whiteDisplayFormats = WHITE_DISPLAY_FORMATS.split("");

        initDisplayMap(blackDisplayFormats, Color.BLACK);
        initDisplayMap(whiteDisplayFormats, Color.WHITE);
    }

    private PieceDisplayUtil() {
    }

    private static void initDisplayMap(String[] displayFormats, Color color) {
        displayMap.put(new Piece(color, PieceType.PAWN), displayFormats[PAWN_IDX]);
        displayMap.put(new Piece(color, PieceType.KNIGHT), displayFormats[KNIGHT_IDX]);
        displayMap.put(new Piece(color, PieceType.BISHOP), displayFormats[BISHOP_IDX]);
        displayMap.put(new Piece(color, PieceType.ROOK), displayFormats[ROOK_IDX]);
        displayMap.put(new Piece(color, PieceType.QUEEN), displayFormats[QUEEN_IDX]);
        displayMap.put(new Piece(color, PieceType.KING), displayFormats[KING_IDX]);
    }

    public static String toDisplay(Piece piece) {
        return displayMap.getOrDefault(piece, EMPTY_DISPLAY_FORMAT);
    }
}
