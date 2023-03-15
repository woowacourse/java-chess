package chess;

import chess.controller.ChessGameController;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController(
                new OutputView(),
                ChessGame.initialize()
        );
        chessGameController.run();
    }
}
