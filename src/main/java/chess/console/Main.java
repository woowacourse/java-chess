package chess.console;

import chess.console.controller.ChessController;
import chess.console.service.ChessService;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ChessService());
        chessController.run();
    }
}
