package chess;

import chess.controller.ChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        try {
            ChessController chessController = new ChessController();
            chessController.run();
        } catch (RuntimeException e) {
        }
    }
}
