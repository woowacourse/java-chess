package chess;

import chess.controller.ChessController;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            ChessController.run();
        } catch (Exception e) {
            OutputView.print(e.getMessage());
        }
    }
}
