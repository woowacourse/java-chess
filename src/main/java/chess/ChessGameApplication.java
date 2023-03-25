package chess;

import chess.controller.ChessGameController;
import chess.view.OutputView;

public class ChessGameApplication {
    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController();
        try {
            chessGameController.run();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
        }
    }
}
