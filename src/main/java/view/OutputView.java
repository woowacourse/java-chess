package view;

import chess.Shape;
import chess.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final int MIN_CHESS_BOARD_NUM = 1;
    private static final int MAX_CHESS_BOARD_NUM = 8;

    public static void printChessBoard(Map<List<Integer>, ChessPiece> chessBoard) {
        for (int vertical = MAX_CHESS_BOARD_NUM; vertical >= MIN_CHESS_BOARD_NUM; vertical--) {
            circuitHorizontal(chessBoard, vertical);
            System.out.println();
        }
    }

    private static void circuitHorizontal(Map<List<Integer>, ChessPiece> chessBoard, int vertical) {
        for (int horizontal = MIN_CHESS_BOARD_NUM; horizontal <= MAX_CHESS_BOARD_NUM; horizontal++) {
            System.out.print(Shape.getNameByClass(chessBoard.get(List.of(horizontal, vertical))));
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
