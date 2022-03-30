package chess.controller;

import chess.Command;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        do {
            runTurn(chessGame);
        } while (chessGame.isNotEnded());
    }

    private void runTurn(final ChessGame chessGame) {
        try {
            printTurnMessage(chessGame);
            executeCommand(chessGame, InputView.requestCommands());
        } catch (final IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void printTurnMessage(final ChessGame chessGame) {
        if (chessGame.isStarted()) {
            OutputView.printTurnMessage(chessGame.getTurnName());
        }
    }

    private void executeCommand(final ChessGame chessGame, final List<String> commands) {
        final Command command = Command.of(commands.get(0));
        command.execute(chessGame, commands);
    }

}
