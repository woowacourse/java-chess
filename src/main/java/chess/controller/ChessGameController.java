package chess.controller;

import chess.domain.game.ChessGameProgressor;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputInitialCommand()) {
            ChessGameProgressor chessGameProgressor = new ChessGameProgressor();
            printCurrentBoard(chessGameProgressor);
            progressChessGame(chessGameProgressor);
        }
    }

    private void progressChessGame(final ChessGameProgressor chessGameProgressor) {
        while (chessGameProgressor.isOn()) {
            List<String> inputs = InputView.inputProgressCommand();
            chessGameProgressor.progress(inputs);
            printCurrentBoard(chessGameProgressor);
        }
    }

    private void printCurrentBoard(final ChessGameProgressor chessGameProgressor) {
        if (chessGameProgressor.isOn()) {
            OutputView.printBoard(chessGameProgressor.getCurrentBoard());
        }
    }
}
