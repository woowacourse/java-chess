package chess.view;

import chess.Chessboard;
import chess.piece.Piece;

import java.util.List;

public class OutputView {

    public static void printBoard(Chessboard chessboard) {
        List<List<Piece>> board = chessboard.getBoard();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                System.out.print(board.get(i).get(j).getType());
            }
            System.out.println();
        }

    }
}
