package chess;

import chess.game.Command;
import chess.game.Game;
import chess.status.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final Game game = new Game(Ready.start(Command.of(InputView.inputCommand())));
        while (game.isRunning()) {
            OutputView.printBoard(game.getBoard());
            run(game);
        }
        OutputView.printGameEnd(game.getWinColor());
    }

    private static void run(final Game game) {
        try {
            OutputView.printScore(game.run(InputView.input()));
        } catch (final IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
