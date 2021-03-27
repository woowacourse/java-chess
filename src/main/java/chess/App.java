package chess;

import chess.controller.ChessGameController;

public class App {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController();
        chessGameController.start();
    }
}
