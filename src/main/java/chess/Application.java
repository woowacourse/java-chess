package chess;

import chess.controller.ChessController;
import chess.controller.FrontController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        FrontController frontController = new FrontController(
                new ChessController(new OutputView()),
                new InputView(),
                new OutputView());
        frontController.run();
    }
}
