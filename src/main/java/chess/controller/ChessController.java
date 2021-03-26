package chess.controller;

import chess.domain.CommandAsString;
import chess.domain.board.Game;
import chess.domain.game.GameManager;
import chess.domain.game.GameVisual;
import chess.domain.game.state.InitialState;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    public void run() {
        OutputView.printGuideMessage();
        GameManager gameManager = new GameManager(new CommandAsString(), new InitialState(Game.initiate()));
        while (gameManager.isNotFinished()) {
            try {
                final CommandAsString command = new CommandAsString(InputView.receiveInput());
                gameManager = gameManager.execute(command);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
            final GameVisual gameVisual = gameManager.gameVisual();
            OutputView.print(gameVisual);
        }
        OutputView.printFinishedMessage();
    }
}
