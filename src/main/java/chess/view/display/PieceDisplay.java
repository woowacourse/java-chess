package chess.view.display;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceDisplay {

    BLACK_KING(King.class, Color.BLACK, "K"),
    BLACK_QUEEN(Queen.class, Color.BLACK, "Q"),
    BLACK_ROOK(Rook.class, Color.BLACK, "R"),
    BLACK_BISHOP(Bishop.class, Color.BLACK, "B"),
    BLACK_KNIGHT(Knight.class, Color.BLACK, "N"),
    BLACK_PAWN(Pawn.class, Color.BLACK, "P"),
    WHITE_KING(King.class, Color.WHITE, "k"),
    WHITE_QUEEN(Queen.class, Color.WHITE, "q"),
    WHITE_ROOK(Rook.class, Color.WHITE, "r"),
    WHITE_BISHOP(Bishop.class, Color.WHITE, "b"),
    WHITE_KNIGHT(Knight.class, Color.WHITE, "n"),
    WHITE_PAWN(Pawn.class, Color.WHITE, "p"),
    EMPTY(Empty.class, Color.EMPTY, ".");

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
                .filter(notation -> PieceDisplay.isPieceMatchesNotation(piece, notation))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isPieceMatchesNotation(Piece piece, PieceDisplay notation) {
        if (piece == null) {
            return false;
        }
        return piece.hasColorOf(notation.color) && isSamePieceType(notation.pieceType, piece);
    }

    private static boolean isSamePieceType(Class<? extends Piece> pieceType, Piece piece) {
        return pieceType.isInstance(piece);
    }
}
