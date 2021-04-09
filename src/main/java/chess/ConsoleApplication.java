package chess;

import chess.controller.ChessController;

public class ConsoleApplication {
    public static void main(String[] args) {
        try {
            ChessController chessController = new ChessController();
            chessController.run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}