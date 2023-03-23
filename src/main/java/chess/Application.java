package chess;

import chess.controller.ChessController;
import chess.view.InputView;

public class Application {

    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new InputView());
        chessController.run();
    }
}
