package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Row;
import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class OutputView {
    public static void printBoard(ChessBoard chessBoard) {
        List<Row> board = chessBoard.getBoard();
        for (int i = 8; i >= 1; i--) {
            for (int j = 0; j <= 7; j++) {
                Row row = board.get(i - 1);
                ChessPiece chessPiece = row.get(j);
                System.out.print(chessPiece.getName());
            }
            System.out.println();
        }
    }
}
