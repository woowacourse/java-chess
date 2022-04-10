package chess.dto.board;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.util.PieceDisplayUtil;
import java.util.Objects;

public class SquareDto {

    static final String WHITE_PIECE_COLOR_CLASSNAME = "white";
    static final String EMPTY_PIECE_DISPLAY = "";
    static final String EMPTY_CLASSNAME = "";

    final String pieceDisplay;
    final String textColor;

    SquareDto(String pieceDisplay, String textColor) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SquareDto squareDto = (SquareDto) o;
        return Objects.equals(pieceDisplay, squareDto.pieceDisplay)
                && Objects.equals(textColor, squareDto.textColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceDisplay, textColor);
    }

    @Override
    public String toString() {
        return "SquareDto{" + "pieceDisplay='" + pieceDisplay + '\'' + ", textColor='" + textColor + '\'' + '}';
    }
}
