package chess;

import chess.controller.ChessGameController;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessGameController chessGame = new ChessGameController();
        chessGame.run();
    }
}
