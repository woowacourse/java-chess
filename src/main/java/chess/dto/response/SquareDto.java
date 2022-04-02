package chess.dto.response;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.util.PieceDisplayUtil;

public class SquareDto {

    private static final String WHITE_PIECE_COLOR_CLASSNAME = "white";
    private static final String EMPTY_PIECE_DISPLAY = "";
    private static final String EMPTY_CLASSNAME = "";

    private final String pieceDisplay;
    private final String textColor;

    private SquareDto(String pieceDisplay, String textColor) {
        this.pieceDisplay = pieceDisplay;
        this.textColor = textColor;
    }

    public static SquareDto ofOccupied(Piece piece) {
        String pieceDisplay = PieceDisplayUtil.toWebDisplay(piece);
        return new SquareDto(pieceDisplay, toPieceColorIfWhite(piece));
    }

    public static SquareDto ofEmpty() {
        return new SquareDto(EMPTY_PIECE_DISPLAY, EMPTY_CLASSNAME);
    }

    private static String toPieceColorIfWhite(Piece piece) {
        if (piece.hasColorOf(Color.WHITE)) {
           return WHITE_PIECE_COLOR_CLASSNAME;
        }
        return EMPTY_CLASSNAME;
    }

    public String getValue() {
        return pieceDisplay;
    }

    public String getColor() {
        return textColor;
    }
}
