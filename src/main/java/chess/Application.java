package chess;

import chess.controller.ChessController;
import chess.view.input.InputView;
import chess.view.output.ConsoleViewChessBoardPositions;
import chess.view.output.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView(ConsoleViewChessBoardPositions.getInstance());

        ChessController controller = new ChessController(inputView, outputView);
        controller.run();
    }
}
