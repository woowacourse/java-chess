package chess;

import chess.controller.ChessController;
import chess.views.InputView;


public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(InputView.getCommand());
        chessController.play();
    }
}