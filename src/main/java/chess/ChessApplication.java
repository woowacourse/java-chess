package chess;

import chess.controller.ChessController;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        ChessController controller = new ChessController();

        controller.run();
    }
}
