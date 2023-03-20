package chess;

import chess.controller.ChessController;
import chess.game.ChessGame;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ChessGame());
        chessController.run();
    }
}
