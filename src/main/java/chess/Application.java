package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(new InputView(), new OutputView());
        try {
            controller.run();
        } catch (Exception exception) {
            controller.printErrorMessage(exception);
        }
    }
}
