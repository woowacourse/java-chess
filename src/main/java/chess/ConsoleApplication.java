package chess;

import chess.status.Ready;
import chess.status.State;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final State state = Ready.run(Command.of(InputView.inputCommand()));
        final Game game = new Game(state);

        if (game.isRunning()) {
            OutputView.printBoard(state.getBoard());
        }

        while (game.isRunning()) {
            run(game);
            OutputView.printBoard(state.getBoard());
        }

        OutputView.printGameEnd(game.getWinColor());
    }

    private static void run(final Game game) {
        try {
            OutputView.printScore(game.run(InputView.input()));
        } catch (final IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
