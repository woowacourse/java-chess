package chess.controller;

import chess.domain.game.ChessGameProgressor;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputInitialCommand()) {
            ChessGameProgressor chessGameProgressor = new ChessGameProgressor();
            OutputView.printBoard(chessGameProgressor.getCurrentBoard());
        }
    }
}
