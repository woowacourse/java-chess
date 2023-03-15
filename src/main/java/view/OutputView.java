package view;

import chess.ChessBoard;
import chess.Shape;
import chess.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printChessBoard(Map<List<Integer>, ChessPiece> chessBoard) {
        for (int j = 8; j >= 1; j--) {
            for (int i = 1; i <= 8; i++) {
                System.out.print(Shape.getNameByClass(chessBoard.get(List.of(i, j))));
            }
            System.out.println();
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
