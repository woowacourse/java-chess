package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ViewChessBoardPosition;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(new InputView(), new OutputView(ViewChessBoardPosition.create()));

        controller.run();
    }
}
