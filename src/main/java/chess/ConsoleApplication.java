package chess;

import chess.game.Game;
import chess.status.Ready;
import chess.status.State;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final Game game = new Game(ready());
        while (game.isRunning()) {
            OutputView.printBoard(game.getBoard());
            run(game);
        }
        OutputView.printGameEnd(game.getWinColor());
    }

    private static State ready() {
        try {
            return Ready.start(Command.of(InputView.inputCommand()));
        } catch (final IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return ready();
        }
    }

    private static void run(final Game game) {
        try {
            OutputView.printScore(game.run(InputView.input()));
        } catch (final IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
