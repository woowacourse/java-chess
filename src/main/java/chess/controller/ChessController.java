package chess.controller;

import chess.domain.Game;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        Game game = new Game();
        while (!game.isEnd()) {
            selectMenu(game);
        }
    }

    private void selectMenu(final Game game) {
        try {
            String input = InputView.receiveInitialResponse();
            Command command = Commands.matchCommand(input);
            command = command.run(game, input);
            showCommandResult(command, game);
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
        }
    }

    private void showCommandResult(Command command, Game game) {
        if (command.isStartCommand() || command.isMoveCommand()) {
            OutputView.printBoard(game);
            return;
        }
        if (command.isEndCommand()) {
            OutputView.printGameWinner(game);
            return;
        }
        OutputView.printScore(game);
    }
}
