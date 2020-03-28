package chess;

import chess.controller.ChessGame;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            ChessGame.run();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
