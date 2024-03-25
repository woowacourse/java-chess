package chess.view;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;

public class PieceTextConverter {
    private PieceTextConverter() {
    }

    public static String convertToText(Piece piece) {
        if (piece instanceof King) {
            return getText(piece, "k", "K");
        }
        if (piece instanceof Queen) {
            return getText(piece, "q", "Q");
        }
        if (piece instanceof Bishop) {
            return getText(piece, "b", "B");
        }
        if (piece instanceof Rook) {
            return getText(piece, "r", "R");
        }
        if (piece instanceof Knight) {
            return getText(piece, "n", "N");
        }
        if (piece instanceof Pawn) {
            return getText(piece, "p", "P");
        }
        if (piece instanceof Empty) {
            return getText(piece, ".", ".");
        }
        throw new IllegalStateException("기물 텍스트가 존재하지 않습니다.");
    }

    private static String getText(final Piece piece, final String whiteText, final String blackText) {
        if (piece.getSide().isWhite()) {
            return whiteText;
        }
        return blackText;
    }
}
