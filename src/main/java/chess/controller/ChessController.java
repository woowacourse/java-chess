package chess.controller;

import chess.domain.ChessContext;
import chess.domain.board.Board;
import chess.domain.judge.BasicJudge;
import chess.domain.judge.Judge;
import chess.exceptions.InvalidInputException;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static Board board = Board.init();
    private static Judge judge = new BasicJudge(board);
    private static ChessContext context = new ChessContext(board, judge);

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
        if (judge.isGameOver()) {
            OutputView.showGameOver(judge);
            System.exit(0);
        }
    }
}
