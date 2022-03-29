package chess;

import chess.controller.ChessGameController;

public class ConsoleApplication {

    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController();
        chessGameController.run();
    }
}
