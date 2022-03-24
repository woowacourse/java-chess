package chess.controller;

import chess.domain.game.ChessGameProgress;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputInitialCommand()) {
            ChessGameProgress chessGameProgress = new ChessGameProgress();
            printCurrentBoard(chessGameProgress);
            progressChessGame(chessGameProgress);
        }
    }

    private void progressChessGame(final ChessGameProgress chessGameProgress) {
        while (chessGameProgress.isOn()) {
            List<String> inputs = InputView.inputProgressCommand();
            chessGameProgress.progress(inputs);
            printCurrentBoard(chessGameProgress);
        }
    }

    private void printCurrentBoard(final ChessGameProgress chessGameProgress) {
        if (chessGameProgress.isOn()) {
            OutputView.printBoard(chessGameProgress.getCurrentBoard());
        }
    }
}
