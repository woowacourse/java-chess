package controller;

import controller.command.Command;
import controller.command.Commands;
import domain.board.Board;
import domain.board.BoardInitiator;
import domain.game.Game;
import view.CommandShape;
import view.InputView;
import view.OutputView;

public class ChessController {
    public void start() {
        final Board board = new Board(BoardInitiator.init());
        Game game = new Game(board);

        while (game.isNotEnded()) {
            game = new Game(board);
            Commands commands = Commands.of(game);
            readyForStart(game, commands);
            runningGame(game, commands);
        }
    }

    private void readyForStart(final Game game, final Commands commands) {
        OutputView.printGameStartMessage();
        while (game.isInit()) {
            executeCommand(commands);
        }
    }

    private void runningGame(final Game game, final Commands commands) {
        while (game.isStarted()) {
            executeCommand(commands);
        }
    }

    private void executeCommand(final Commands commands) {
        try {
            final String value = InputView.inputCommand();
            Command command = commands.find(CommandShape.of(value));
            command.execute();
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
