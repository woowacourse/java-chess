package chess;

import chess.controller.ChessController;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessController controller = new ChessController(new InputView(), new OutputView());
        controller.run();
    }
}
