package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Row;
import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class OutputView {
    public static void printBoard(ChessBoard chessBoard) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 0; j <= 7; j++) {
                List<Row> rows = chessBoard.getBoard();
                Row row = rows.get(i - 1);
                List<ChessPiece> chessPieces = row.getChessPieces();
                ChessPiece chessPiece = chessPieces.get(j);
                System.out.print(chessPiece.getName());
            }
            System.out.println();
        }
    }
}
