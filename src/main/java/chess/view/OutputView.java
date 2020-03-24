package chess.view;

import chess.domain.PiecePosition;
import chess.domain.chessboard.ChessBoard;

public class OutputView {
    private static final String EMPTY_MARK = ".";

    public static void printChessBoard(ChessBoard chessBoard) {
        int temp = 0;
        for (PiecePosition piecePosition : chessBoard.getChessBoard()) {
            seperateLine(temp);
            if (piecePosition.getPiece() == null) {
                System.out.print(EMPTY_MARK);
                temp++;
                continue;
            }
            System.out.print(piecePosition.getPieceName());
            temp++;
        }
    }

    private static void seperateLine(int temp) {
        if (temp % 8 == 0) {
            System.out.println();
        }
    }
}
