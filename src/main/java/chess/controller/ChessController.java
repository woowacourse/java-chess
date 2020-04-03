package chess.controller;

import chess.domain.Game;
import chess.exceptions.InvalidInputException;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static Game context = new Game();

    public static void run() {
        OutputView.instruction();
        while (true) {
            String input = InputView.getInput();
            tryToExecute(input);
            validateGameOver();
        }
    }

    public static void tryToExecute(final String input) {
        try {
            Command.of(input).execute(input, context);
        } catch (InvalidInputException e) {
            OutputView.showError(e);
        }
    }

    private static void validateGameOver() {
        if (context.isGameOver()) {
            OutputView.showGameOver(context.winnerSide());
            System.exit(0);
        }
    }
}
