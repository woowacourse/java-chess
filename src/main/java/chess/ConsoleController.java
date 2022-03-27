package chess;

import chess.domain.command.CommandState;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();

        CommandState command = inputCommand();
        while (command.isStart()) {
            command = runCommand(command);
        }
    }

    private CommandState inputCommand() {
        try {
            return CommandState.of(InputView.requestStartOrEnd());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private CommandState runCommand(CommandState command) {
        OutputView.printBoard(command.getBoard());
        command = inputCommandAndExecute(command);

        if (command.isStatus()) {
            OutputView.printStatus(command.getStatus());
        }
        return command;
    }

    private CommandState inputCommandAndExecute(CommandState command) {
        try {
            command = command.execute(InputView.requestCommand());
            return command;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommandAndExecute(command);
        }
    }
}
