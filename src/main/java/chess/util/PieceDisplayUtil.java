package chess.util;

import chess.domain.board.piece.Color;
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

        static final String[] BLACK_DISPLAY_FORMATS = "♙♘♗♖♕♔".split("");
        static final String[] WHITE_DISPLAY_FORMATS = "♟♞♝♜♛♚".split("");
        static final String EMPTY_DISPLAY_FORMAT = ".";

        static final String[] COLORLESS_DISPLAY_FORMATS = "♟♞♝♜♛♚".split("");
        static final String WEB_EMPTY_DISPLAY_FORMAT = "";

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

        static void putEachPiece(String[] displayFormats, Map<Piece, String> displayMap, Color color) {
            PieceType[] pieceTypes = PieceType.values();
            for (int idx = 0; idx < pieceTypes.length; idx++ ){
                Piece piece = Piece.of(color, pieceTypes[idx]);
                displayMap.put(piece, displayFormats[idx]);
            }
        }

        static String displayOf(Piece piece) {
            return displayMap.getOrDefault(piece, EMPTY_DISPLAY_FORMAT);
        }

        static String colorlessDisplayOf(Piece piece) {
            return colorlessDisplayMap.getOrDefault(piece, WEB_EMPTY_DISPLAY_FORMAT);
        }
    }
}
