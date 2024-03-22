package chess.view.display;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Arrays;

public enum PieceDisplay {

    BLACK_KING(King.class, Color.BLACK, "K"),
    BLACK_QUEEN(Queen.class, Color.BLACK, "Q"),
    BLACK_ROOK(Rook.class, Color.BLACK, "R"),
    BLACK_KNIGHT(Knight.class, Color.BLACK, "N"),
    BLACK_BISHOP(Bishop.class, Color.BLACK, "B"),
    BLACK_PAWN(Pawn.class, Color.BLACK, "P"),
    WHITE_KING(King.class, Color.WHITE, "k"),
    WHITE_QUEEN(Queen.class, Color.WHITE, "q"),
    WHITE_ROOK(Rook.class, Color.WHITE, "r"),
    WHITE_KNIGHT(Knight.class, Color.WHITE, "n"),
    WHITE_BISHOP(Bishop.class, Color.WHITE, "b"),
    WHITE_PAWN(Pawn.class, Color.WHITE, "p"),
    EMPTY(null, null, ".");

    private final Class<? extends Piece> pieceType;
    private final Color color;
    private final String notation;

    PieceDisplay(Class<? extends Piece> pieceType, Color color, String notation) {
        this.pieceType = pieceType;
        this.color = color;
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public static PieceDisplay getNotationByPiece(Piece piece) {
        return Arrays.stream(values())
                .filter(display -> PieceDisplay.isPieceMatchesNotation(piece, display))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isPieceMatchesNotation(Piece piece, PieceDisplay display) {
        if (piece == null) {
            return false;
        }
        return display.isAbleToRepresent(piece) && piece.hasColorOf(display.color);
    }

    private boolean isAbleToRepresent(Piece piece) {
        return pieceType.isInstance(piece);
    }
}
