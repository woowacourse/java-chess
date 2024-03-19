package chess;

import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.StartCommand;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        StartCommand command = inputView.askStartMessage();
        if (command.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
    }
}
