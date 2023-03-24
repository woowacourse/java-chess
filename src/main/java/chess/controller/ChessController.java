package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.controller.command.CommandHistory;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CommandHistory commandHistory = new CommandHistory();
        outputView.printGameGuide();
        ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
        while (chessGame.isNotEnd()) {
            executeCommand(chessGame, commandHistory);
        }
        outputView.printWinningTeam(chessGame.findWinningTeam());
    }

    private void executeCommand(final ChessGame chessGame, final CommandHistory commandHistory) {
        try {
            Command command = CommandFactory.from(inputView.readCommandAndParameters());
            command.execute(chessGame, outputView);
            commandHistory.addHistory(command);
        } catch (IllegalArgumentException | UnsupportedOperationException | IllegalStateException e) {
            outputView.printError(e.getMessage());
            executeCommand(chessGame, commandHistory);
        }
    }

}
