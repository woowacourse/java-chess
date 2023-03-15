package chess;

import chess.controller.ChessController;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(new OutputView());
        controller.printBoard();
    }
}
