package chess.view;

import chess.domain.piece.Piece;

public class PieceView {

    public static String from(Piece piece) {
        return kingSymbol(piece)
                + queenSymbol(piece)
                + bishopSymbol(piece)
                + rookSymbol(piece)
                + knightSymbol(piece)
                + pawnSymbol(piece);
    }

    private static String kingSymbol(Piece piece) {
        if (!piece.isKing()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♚";
        }
        return "♔";
    }

    private static String queenSymbol(Piece piece) {
        if (!piece.isQueen()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♛";
        }
        return "♕";
    }

    private static String bishopSymbol(Piece piece) {
        if (!piece.isBishop()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♝";
        }
        return "♗";
    }

    private static String rookSymbol(Piece piece) {
        if (!piece.isRook()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♜";
        }
        return "♖";
    }

    private static String pawnSymbol(Piece piece) {
        if (!piece.isPawn()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♟";
        }
        return "♙";
    }

    private static String knightSymbol(Piece piece) {
        if (!piece.isKnight()) {
            return "";
        }
        if (piece.isWhite()) {
            return "♞";
        }
        return "♘";
    }
}
