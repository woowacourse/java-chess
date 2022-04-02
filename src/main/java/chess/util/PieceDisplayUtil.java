package chess.util;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.NonPawn;
import chess.domain.board.piece.Pawn;
import chess.domain.board.piece.Piece;
import chess.domain.board.piece.PieceType;
import java.util.HashMap;
import java.util.Map;

public class PieceDisplayUtil {

    private PieceDisplayUtil() {
    }

    public static String toDisplay(Piece piece) {
        return PieceDisplayMap.displayOf(piece);
    }

    public static String toWebDisplay(Piece piece) {
        return PieceDisplayMap.colorlessDisplayOf(piece);
    }

    private static class PieceDisplayMap {

        static final String BLACK_DISPLAY_FORMATS = "♙♘♗♖♕♔";
        static final String WHITE_DISPLAY_FORMATS = "♟♞♝♜♛♚";
        static final String EMPTY_DISPLAY_FORMAT = ".";

        static final String COLORLESS_DISPLAY_FORMATS = "♟♞♝♜♛♚";
        static final String WEB_EMPTY_DISPLAY_FORMAT = "";

        static final int PAWN_IDX = 0;
        static final int KNIGHT_IDX = 1;
        static final int BISHOP_IDX = 2;
        static final int ROOK_IDX = 3;
        static final int QUEEN_IDX = 4;
        static final int KING_IDX = 5;

        static final Map<Piece, String> displayMap;
        static final Map<Piece, String> colorlessDisplayMap;

        static {
            displayMap = initDisplayMap();
            colorlessDisplayMap = initColorlessDisplayMap();
        }

        static Map<Piece, String> initDisplayMap() {
            Map<Piece, String> displayMap = new HashMap<>();
            putEachPiece(BLACK_DISPLAY_FORMATS, displayMap, Color.BLACK);
            putEachPiece(WHITE_DISPLAY_FORMATS, displayMap, Color.WHITE);
            return displayMap;
        }

        static Map<Piece, String> initColorlessDisplayMap() {
            Map<Piece, String> colorlessDisplayMap = new HashMap<>();
            for (Color color : Color.values()) {
                putEachPiece(COLORLESS_DISPLAY_FORMATS, colorlessDisplayMap, color);
            }
            return colorlessDisplayMap;
        }

        static void putEachPiece(String displayFormat, Map<Piece, String> displayMap, Color color) {
            String[] displayFormats = displayFormat.split("");
            displayMap.put(new Pawn(color), displayFormats[PAWN_IDX]);
            displayMap.put(new NonPawn(color, PieceType.KNIGHT), displayFormats[KNIGHT_IDX]);
            displayMap.put(new NonPawn(color, PieceType.BISHOP), displayFormats[BISHOP_IDX]);
            displayMap.put(new NonPawn(color, PieceType.ROOK), displayFormats[ROOK_IDX]);
            displayMap.put(new NonPawn(color, PieceType.QUEEN), displayFormats[QUEEN_IDX]);
            displayMap.put(new NonPawn(color, PieceType.KING), displayFormats[KING_IDX]);
        }

        static String displayOf(Piece piece) {
            return displayMap.getOrDefault(piece, EMPTY_DISPLAY_FORMAT);
        }

        static String colorlessDisplayOf(Piece piece) {
            return colorlessDisplayMap.getOrDefault(piece, WEB_EMPTY_DISPLAY_FORMAT);
        }
    }
}
