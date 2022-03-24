package chess;

import chess.view.InputView;
import chess.view.OutputView;
import chess.view.command.Command;
import chess.view.command.Ready;

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
