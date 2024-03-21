package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;

public class PieceMarkFormatter {

    public static String convertToMark(final Piece piece) {
        if (piece instanceof Rook && piece.isBlack()) {
            return "R";
        }
        if (piece instanceof Rook && piece.isWhite()) {
            return "r";
        }
        if (piece instanceof Night && piece.isBlack()) {
            return "N";
        }
        if (piece instanceof Night && piece.isWhite()) {
            return "n";
        }
        if (piece instanceof Bishop && piece.isBlack()) {
            return "B";
        }
        if (piece instanceof Bishop && piece.isWhite()) {
            return "b";
        }
        if (piece instanceof Queen && piece.isBlack()) {
            return "Q";
        }
        if (piece instanceof Queen && piece.isWhite()) {
            return "q";
        }
        if (piece instanceof King && piece.isBlack()) {
            return "K";
        }
        if (piece instanceof King && piece.isWhite()) {
            return "k";
        }
        if (piece instanceof Pawn && piece.isBlack()) {
            return "P";
        }
        if (piece instanceof Pawn && piece.isWhite()) {
            return "p";
        }
        return "";
    }
}
