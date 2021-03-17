package view;

import domain.chess.Board;
import domain.chess.piece.Piece;

public class OutputView {
    public static void showBoard(Board board) {
        Piece pieces[][] = board.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = pieces[i][j];
                if (piece == null) {
                    System.out.print(". ");
                    continue;
                }
                if (piece.isBlack())
                    System.out.print(piece.getName().toUpperCase() + " ");
                else
                    System.out.print(piece.getName() + " ");
            }
            System.out.println();
        }
    }
}
