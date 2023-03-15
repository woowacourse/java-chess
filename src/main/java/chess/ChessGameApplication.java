package chess;

import chess.controller.ChessGameController;
import chess.controller.Retry;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController(
                new InputView(),
                new OutputView(),
                ChessGame.initialize()
        );
        chessGameController.run(new Retry(5));
    }
}
