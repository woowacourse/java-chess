package chess;

import chess.dto.CommandRequest;
import chess.game.Command;
import chess.game.Game;
import chess.state.Ready;
import chess.state.State;
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
            return Ready.start(Command.of(InputView.inputStartCommand()));
        } catch (final IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return ready();
        }
    }

    private static void run(final Game game) {
        try {
            final CommandRequest commandRequest = InputView.inputPlayCommand();
            OutputView.printScore(game.run(commandRequest));
        } catch (final IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
