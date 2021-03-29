package chess.controller;

import chess.domain.CommandAsString;
import chess.domain.game.GameManager;
import chess.domain.game.Result;
import chess.domain.game.state.InitialState;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    public void run() {
        OutputView.printGuideMessage();
        GameManager gameManager = new GameManager(new InitialState());
        while (gameManager.isNotFinished()) {
            try {
                final CommandAsString command = new CommandAsString(InputView.receiveInput());
                gameManager = gameManager.execute(command);
                final Result result = gameManager.result(command);
                OutputView.print(result.visualAsString());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
        }
        OutputView.printFinishedMessage();
    }
}
