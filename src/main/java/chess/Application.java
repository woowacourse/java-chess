package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;

public class Application {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();

        ChessController chessController = new ChessController();
        chessController.run(chessGame);
    }
}
