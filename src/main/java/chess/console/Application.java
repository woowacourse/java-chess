package chess.console;

import chess.console.controller.ChessController;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }

}
