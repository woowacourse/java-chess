package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printGameStartMessage();

        ChessGame game = new ChessGame();
        while (!game.isFinished()) {
            play(game);
            OutputView.printResult(game.getProcessResult());
        }
    }

    private void play(ChessGame game) {
        try {
            String input = InputView.inputCommand();
            game.play(input);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            play(game);
        }
    }
}
