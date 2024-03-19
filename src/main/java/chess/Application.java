package chess;

import chess.controller.ChessController;
import chess.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ChessController chessController = new ChessController(inputView);
        chessController.run();
    }
}
