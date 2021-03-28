package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.Start;
import chess.manager.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String COMMAND_TO_START = "start";

    private final ChessGame chessGame = new ChessGame();
    private Command command;

    public void run() {
        readStart();
        executeUserCommands();
        OutputView.printWinner(chessGame.winner());
    }

    private void readStart() {
        do {
            OutputView.printMenu();
        } while (!COMMAND_TO_START.equals(InputView.readUserInput()));

        command = new Start(COMMAND_TO_START);
        command.execute(chessGame);
    }

    private void executeUserCommands() {
        do {
            readUserCommand();
        } while (!chessGame.isEnd());
    }

    private void readUserCommand() {
        try {
            command = command.read(InputView.readUserInput());
            command.execute(chessGame);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            readUserCommand();
        }
    }
}
