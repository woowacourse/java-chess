package chess;

import chess.controller.ChessController;
import chess.controller.ErrorController;

public final class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ErrorController());
        chessController.run();
    }
}
