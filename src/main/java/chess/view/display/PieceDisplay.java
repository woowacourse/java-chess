package chess.view.display;

import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceAttributes;
import chess.piece.PieceType;
import java.util.Arrays;

public enum PieceDisplay {

    BLACK_KING(PieceType.KING, Color.BLACK, "K"),
    BLACK_QUEEN(PieceType.QUEEN, Color.BLACK, "Q"),
    BLACK_ROOK(PieceType.ROOK, Color.BLACK, "R"),
    BLACK_KNIGHT(PieceType.KNIGHT, Color.BLACK, "N"),
    BLACK_BISHOP(PieceType.BISHOP, Color.BLACK, "B"),
    BLACK_PAWN(PieceType.PAWN, Color.BLACK, "P"),
    WHITE_KING(PieceType.KING, Color.WHITE, "k"),
    WHITE_QUEEN(PieceType.QUEEN, Color.WHITE, "q"),
    WHITE_ROOK(PieceType.ROOK, Color.WHITE, "r"),
    WHITE_KNIGHT(PieceType.KNIGHT, Color.WHITE, "n"),
    WHITE_BISHOP(PieceType.BISHOP, Color.WHITE, "b"),
    WHITE_PAWN(PieceType.PAWN, Color.WHITE, "p"),
    EMPTY(null, null, ".");

    private final PieceAttributes pieceAttributes;
    private final String notation;

    PieceDisplay(PieceType pieceType, Color color, String notation) {
        this.pieceAttributes = new PieceAttributes(pieceType, color);
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public static PieceDisplay getNotationByPiece(Piece piece) {
        return Arrays.stream(values())
                .filter(notation -> PieceDisplay.isPieceMatchesNotation(piece, notation))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isPieceMatchesNotation(Piece piece, PieceDisplay notation) {
        if (piece == null) {
            return false;
        }
        return piece.hasAttributesOf(notation.pieceAttributes);
    }
}
