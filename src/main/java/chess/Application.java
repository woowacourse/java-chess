package chess;

import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Command runCommand;
        do {
            runCommand = resolveCommand();
        } while (runCommand == null);

        if (runCommand.isStart()) {
            ChessController.run();
        }
        if (runCommand.isEnd()) {
            System.exit(0);
        }
    }

    private static Command resolveCommand() {
        try {
            return Command.of(InputView.requestRunCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
