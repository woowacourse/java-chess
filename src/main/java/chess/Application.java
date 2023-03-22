package chess;

import chess.controller.ChessController;
import chess.view.input.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        ChessController controller = new ChessController(inputView, outputView);
        controller.run();
    }
}
