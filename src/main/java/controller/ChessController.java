package controller;

import controller.command.Command;
import controller.command.Commands;
import domain.board.Board;
import domain.board.BoardInitiator;
import domain.game.Game;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {
    public static final String DELIMITER = " ";
    public static final int COMMAND_INDEX = 0;

    public void start() {
        final Board board = new Board(BoardInitiator.init());
        Game game = new Game(board);

        while (game.isNotEnded()) {
            game = new Game(board);
            readyForStart(game);
            runningGame(game);
        }
    }

    private void readyForStart(final Game game) {
        OutputView.printGameStartMessage();
        while (game.isInit()) {
            executeCommand(game);
        }
    }

    private void runningGame(final Game game) {
        while (game.isStarted()) {
            executeCommand(game);
        }
    }

    private void executeCommand(final Game game) {
        try {
            final String value = InputView.inputCommand();
            List<String> commandTokens = parseInputValueToCommandTokens(value);
            execute(game, commandTokens);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void execute(final Game game, final List<String> commandTokens) {
        String commandValue = getCommandValueIn(commandTokens);
        Commands commands = Commands.of(game);
        Command command = commands.find(commandValue);
        command.execute(commandTokens);
    }

    private List<String> parseInputValueToCommandTokens(final String value) {
        return List.of(value.split(DELIMITER));
    }

    private String getCommandValueIn(final List<String> commandTokens) {
        return commandTokens.get(COMMAND_INDEX);
    }
}
