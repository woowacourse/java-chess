package chess.controller.console;

import chess.controller.console.command.Command;
import chess.controller.console.command.Start;
import chess.domain.ChessGame;
import chess.view.console.InputView;
import chess.view.console.OutputView;

public class ChessController {
    private static final String COMMAND_TO_START = "start";

    private final ChessGame chessGame = ChessGame.initNew();
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
        } while (!chessGame.isGameEnd());
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
