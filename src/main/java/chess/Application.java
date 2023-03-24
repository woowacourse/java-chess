package chess;

import chess.controller.ChessController;

public final class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        try {
            chessController.run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
