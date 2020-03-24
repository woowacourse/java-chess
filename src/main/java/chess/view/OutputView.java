package chess.view;

import chess.domain.PiecePosition;
import chess.domain.chessboard.ChessBoard;

public class OutputView {
    public static void printChessBoard(ChessBoard chessBoard) {
        int temp = 0;
        for (PiecePosition piecePosition : chessBoard.getChessBoard()) {
            if(temp % 8 == 0) {
                System.out.println();
            }
            if (piecePosition.getPiece() == null) {
                System.out.print(".");
                temp++;
                continue;
            }
            System.out.print(piecePosition.getPieceName());
            temp++;
        }
    }
}
