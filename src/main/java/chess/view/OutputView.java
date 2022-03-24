package chess.view;

import chess.piece.Piece;

import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void printBoard(List<Piece> board) {
        Collections.sort(board);
        for (Piece piece : board) {
            System.out.print(piece.getName());
            makeNewLine(piece);
        }
    }

    private static void makeNewLine(Piece piece) {
        if (piece.isLastFile()) {
            System.out.println();
        }
    }
}
