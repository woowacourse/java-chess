package chess;

import chess.controller.ChessController;
import chess.domain.position.component.Row;

public class Application {
    public static void main(String[] args) {
        System.out.println(Row.getBigger(Row.A, Row.B));
        System.out.println(Row.compareTo(Row.A, Row.B));
        System.out.println(Row.getSmaller(Row.A, Row.B));
        System.out.println(Row.compareTo(Row.A, Row.B));
        ChessController chessController = new ChessController();
        chessController.play();
    }
}