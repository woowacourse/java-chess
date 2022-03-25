package chess.view;

import chess.piece.Piece;
import chess.piece.Pieces;

import java.util.Collections;

public class OutputView {
    public static void printBoard(Pieces board) {
        Collections.sort(board.getPieces());
        for (Piece piece : board.getPieces()) {
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
