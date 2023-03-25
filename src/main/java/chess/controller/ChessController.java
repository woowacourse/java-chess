package chess.controller;

import chess.controller.command.Command;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(
            final InputView inputView,
            final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        final var chessGame = new ChessGame();
        outputView.startMessage();
        while (chessGame.isOnGoing()) {
            repeatTurns(chessGame);
        }
    }

    private void repeatTurns(ChessGame chessGame) {
        try {
            Command command = inputView.inputCommand();
            command.execute(chessGame);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
            outputView.printGuideMessage();
            repeatTurns(chessGame);
        }
    }
}
