package chess;

import chess.controller.ChessController;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        try {
            chessController.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
