package chess.console;

import chess.console.command.Command;
import chess.console.command.Ready;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ChessGame {

    private Command command;

    public ChessGame() {
        this.command = new Ready();
    }

    public void run() {
        OutputView.printGameGuide();
        runGameUntilEnd();
    }

    private void runGameUntilEnd() {
        while (!command.isEnd()) {
            command = command.run(InputView.requestGameCommand());
        }
    }
}
