package chess.domain.piece;

import chess.domain.Side;
import chess.exception.ChessException;

public class PieceFactory {

    public static Piece createPieceByName(String pieceName) {
        if (pieceName.equals(".")) {
            return new Blank();
        }
        String pieceType = String.valueOf(pieceName.charAt(1));
        Side side;
        if (pieceName.charAt(0) == 'W') {
            side = Side.WHITE;
        } else {
            side = Side.BLACK;
        }
        if (pieceType.equals("R")) {
            return new Rook(side);
        }
        if (pieceType.equals("B")) {
            return new Bishop(side);
        }
        if (pieceType.equals("N")) {
            return new Knight(side);
        }
        if (pieceType.equals("K")) {
            return new King(side);
        }
        if (pieceType.equals("Q")) {
            return new Queen(side);
        }
        if (pieceType.equals("P")) {
            return new Pawn(side);
        }

        throw new ChessException("잘못된 체스 이름입니다.");
    }
}
