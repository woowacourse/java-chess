package chess;

import chess.view.InputView;
import chess.view.OutputView;

class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessController controller = new ChessController(inputView, outputView);

        controller.run();
    }
}
