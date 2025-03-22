package chess.view;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;

public class OutputView {
    private static final String BLACK = "\u001B[0m";
    private static final String WHITE = "\u001B[37m";
    private static final String RED = "\u001B[31m";

    public static void printBoard(Board board) {
        System.out.print(RED + "  A B C D E F G H ");
        System.out.println(BLACK);
        for (int i = 7; i >= 0; --i) {
            System.out.print(RED + (8 - i) + " ");
            for (int j = 0; j < 8; ++j) {
                Position target = new Position(Column.values()[j], Row.values()[i]);
                if (board.hasPieceAt(target)) {
                    Piece piece = board.getPiece(target);
                    if (piece.getColor() == Color.BLACK) {
                        System.out.print(BLACK + piece.getName() + " " + RED);
                    } else {
                        System.out.print(WHITE + piece.getName() + " " + RED);
                    }
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println(BLACK);
        }
    }
}
