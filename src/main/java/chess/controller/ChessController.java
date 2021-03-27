package chess.controller;

import chess.domain.Game;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.command.StatusCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printGuideMessage();
        Game game = new Game();
        while (!game.isFinished()) {
            executeCommand(game);
        }
        OutputView.printFinishedMessage();
    }

    private void executeCommand(Game game) {
        try {
            String input = InputView.receiveInput();
            Command command = Commands.of(input, game);
            command.execute(input);
            printGameInfo(game, command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void printGameInfo(Game game, Command command) {
        printStatus(command);
        printBoard(game);
        printWinner(game);
    }

    private void printWinner(Game game) {
        if (!game.isFinished()) {
            return;
        }
        OutputView.printWinner(game.getWinner());
    }

    private void printBoard(Game game) {
        if (game.isFinished()) {
            return;
        }
        OutputView.printBoard(game.getBoard());
        OutputView.printTurn(game.getTurn());
    }

    private void printStatus(Command command) {
        if (!command.isStatus()) {
            return;
        }
        StatusCommand status = (StatusCommand) command;
        OutputView.printStatus(status.getWhiteScore(), status.getBlackScore());
        OutputView.printPrevail(status.getPrevailPlayer());
    }
}
