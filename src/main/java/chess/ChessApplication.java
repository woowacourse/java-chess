package chess;

import chess.controller.Controller;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Controller controller = new Controller(inputView, outputView);
        controller.run();
    }
}
