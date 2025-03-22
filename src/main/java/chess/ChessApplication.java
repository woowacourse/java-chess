package chess;

import chess.controller.ChessController;
import chess.domain.initializer.JavaChessPieceInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessController controller = new ChessController(
                new InputView(),
                new OutputView(),
                new JavaChessPieceInitializer()
        );
        controller.run();
    }
}
