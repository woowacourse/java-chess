package chess;

import chess.command.Command;
import chess.command.Ready;
import chess.view.InputView;
import chess.view.OutputView;

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
