package chess.view.display;

import chess.piece.Color;
import chess.piece.ColoredPiece;
import chess.piece.ColoredPieceDto;
import chess.piece.Piece;
import java.util.Arrays;

public enum PieceDisplay {

    BLACK_KING(Piece.KING, Color.BLACK, "K"),
    BLACK_QUEEN(Piece.QUEEN, Color.BLACK, "Q"),
    BLACK_ROOK(Piece.ROOK, Color.BLACK, "R"),
    BLACK_KNIGHT(Piece.KNIGHT, Color.BLACK, "N"),
    BLACK_BISHOP(Piece.BISHOP, Color.BLACK, "B"),
    BLACK_PAWN(Piece.PAWN, Color.BLACK, "P"),
    WHITE_KING(Piece.KING, Color.WHITE, "k"),
    WHITE_QUEEN(Piece.QUEEN, Color.WHITE, "q"),
    WHITE_ROOK(Piece.ROOK, Color.WHITE, "r"),
    WHITE_KNIGHT(Piece.KNIGHT, Color.WHITE, "n"),
    WHITE_BISHOP(Piece.BISHOP, Color.WHITE, "b"),
    WHITE_PAWN(Piece.PAWN, Color.WHITE, "p"),
    EMPTY(null, null, ".");;

    private final ColoredPieceDto coloredPieceDto;
    private final String notation;

    PieceDisplay(Piece piece, Color color, String notation) {
        this.coloredPieceDto = new ColoredPieceDto(piece, color);
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public static PieceDisplay getNotationByColoredPiece(ColoredPiece coloredPiece) {
        return Arrays.stream(values())
                .filter(notation -> PieceDisplay.isPieceMatchesNotation(coloredPiece, notation))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isPieceMatchesNotation(ColoredPiece coloredPiece, PieceDisplay notation) {
        if (coloredPiece == null) {
            return false;
        }
        return coloredPiece.isSamePieceAs(notation.piece()) &&
                coloredPiece.hasColorOf(notation.color());
    }

    private Color color() {
        return coloredPieceDto.color();
    }

    private Piece piece() {
        return coloredPieceDto.piece();
    }
}
