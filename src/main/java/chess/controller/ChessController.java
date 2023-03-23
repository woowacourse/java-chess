package chess.controller;

import chess.controller.command.ExecuteCommand;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGame chessGame;

    public ChessController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printStartMessage();
        while (chessGame.isRunning()) {
            playChessGame();
        }
    }

    private void playChessGame() {
        try {
            final ExecuteCommand executeCommand = inputView.readExecuteCommand();
            executeCommand.execute(chessGame, outputView);
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
