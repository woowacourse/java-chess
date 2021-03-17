package chess.controller;

import chess.domain.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        while (true) {
            InputView.receiveInitialPlayerResponse();
            Game game = Game.init();
            OutputView.printBoard(game);
        }
    }
}
