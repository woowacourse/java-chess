package chess.controller;

import chess.domain.CommandAsString;
import chess.domain.game.Game;
import chess.domain.result.Result;
import chess.domain.game.state.InitialState;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    public void run() {
        OutputView.printGuideMessage();
        Game game = new Game(new InitialState());
        while (game.isNotFinished()) {
            try {
                final CommandAsString command = new CommandAsString(InputView.receiveInput());
                game = game.execute(command);
                final Result result = game.result(command);
                OutputView.print(result.visualAsString());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
        }
        OutputView.printFinishedMessage();
    }
}
