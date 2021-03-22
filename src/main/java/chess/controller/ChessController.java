package chess.controller;

import chess.domain.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printGuideMessage();
        Game game = new Game();
        while (!game.isFinished()) {
            executeCommand(InputView.receiveInput(), game);
        }
    }

    private void executeCommand(final String input, final Game game) {
        try {
            Command.of(input).execute(input, game);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }
}
