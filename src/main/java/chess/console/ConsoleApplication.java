package chess.console;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import java.util.List;

public class ConsoleApplication {
    private static final String START_COMMAND = "start";

    public static void main(String[] args) {
        OutputView.startMessage();
        final List<String> commands = InputView.scanCommand();

        if (START_COMMAND.equalsIgnoreCase(commands.get(0))) {
            new ConsoleChessGame().start();
        }
    }
}
