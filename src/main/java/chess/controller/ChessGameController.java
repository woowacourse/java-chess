package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessGameController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run(final Retry retry) {
        initialize(retry);
    }

    private void initialize(final Retry retry) {
        Command command = Command.EMPTY;
        while (command != Command.START) {
            command = readCommand(retry);
        }
        outputView.printBoard(chessGame.getBoard());
    }

    private Command readCommand(Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return Command.from(inputView.readCommand());
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(Retry.RETRY_FAIL_MESSAGE);
    }
}
