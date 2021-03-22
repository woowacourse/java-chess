package chess.controller;

import chess.domain.CommandAsString;
import chess.domain.game.Game2;
import chess.domain.game.GameVisual;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController2 {

    public void run() {
        OutputView.printGuideMessage();
        Game2 game = new Game2();
        while (game.isNotFinished()) {
            singleActivity(game);
        }
        OutputView.printFinishedMessage();
    }

    private void singleActivity(Game2 game) {
        try {
            final CommandAsString command = new CommandAsString(InputView.receiveInput());
            game = game.execute(command);
            final GameVisual gameVisual = game.gameVisual(command);
            OutputView.print(gameVisual);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            singleActivity(game);
        }
    }
}
