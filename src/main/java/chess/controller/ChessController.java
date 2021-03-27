package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.StartCommand;
import chess.manager.ChessManager;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String COMMAND_TO_START = "start";
    private final ChessManager chessManager;
    private Command command;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        readStart();
        executeUserCommands();
        printResult();
    }

    private void readStart() {
        do {
            OutputView.printMenu();
        } while (!COMMAND_TO_START.equals(InputView.readUserInput()));

        command = new StartCommand();
        command.execute(chessManager);
        OutputView.printBoard(chessManager.board());
    }

    private void executeUserCommands() {
        do {
            readUserCommand();
        } while (!chessManager.isEnd());
    }

    private void readUserCommand() {
        try {
            command = command.read(InputView.readUserInput());
            command.execute(chessManager);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            readUserCommand();
        }
    }

    private void printResult() {
        OutputView.printGameResult(chessManager.calculateStatus());
    }
}
