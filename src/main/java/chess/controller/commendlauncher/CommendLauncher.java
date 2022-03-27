package chess.controller.commendlauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public abstract class CommendLauncher {

    protected void go(ChessGame chessGame) {
        try {
            execute(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            go(chessGame);
        }
    }

    protected Command getCommand() {
        String input = InputView.inputCommend();
        return Command.from(input);
    }

    protected abstract void execute(ChessGame chessGame);
}

