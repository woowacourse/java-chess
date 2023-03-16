package chess;

import chess.controller.ChessGameController;
import chess.controller.Retry;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController(ChessGame.initialize());
        try {
            chessGameController.run(new Retry(5));
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
        }
    }
}
