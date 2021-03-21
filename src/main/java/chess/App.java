package chess;

import chess.controller.ChessGameController;

public class App {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        ChessGameController chessGameController = new ChessGameController(chessGame);
        chessGameController.start();
    }
}
