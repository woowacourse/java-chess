package chess.controller;

import chess.manager.ChessManager;
import chess.manager.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        OutputView.printGuideStartGame();
        Command firstCommand = firstCommand();
        if (firstCommand.isEnd()) {
            return;
        }
        Command command;
        do {
            command = getCommand();
        } while (!chessManager.isEnd() && !command.isEnd());

        OutputView.printGameResult(chessManager.getStatus());
    }

    private Command firstCommand() {
        Command command = initFirstCommand();
        command.execute(chessManager, "");
        return command;
    }

    private Command initFirstCommand() {
        try {
            String userInput = InputView.getUserCommand();
            Command command = Command.of(userInput);
            command.isFirstCommand();
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private Command getCommand() {
        try {
            return executeCommand(InputView.getUserCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return getCommand();
        }
    }

    private Command executeCommand(final String input) {
        final Command command = Command.of(input);
        command.execute(chessManager, input);
        return command;
    }
}
