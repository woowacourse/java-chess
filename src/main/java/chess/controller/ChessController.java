package chess.controller;

import static chess.view.Command.*;

import java.util.Arrays;
import java.util.Objects;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.BasicJudge;
import chess.domain.judge.Judge;
import chess.exceptions.InvalidInputException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static Board board = Board.init();
    private static Judge judge = new BasicJudge(board);

    public static void run() {
        OutputView.instruction();
        while (true) {
            String input = InputView.getInput();
            validateCommand(input);
            Arrays.stream(values())
                .forEach(command -> command.accept(input));
            validateGameOver();
        }
    }

    private static void validateCommand(final String command) {
        try {
            validate(command);
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

    public static void initialize(final String input) {
        if (START.getValue().equals(input)) {
            board = Board.init();
            judge = new BasicJudge(board);
            OutputView.showBoard(board);
        }
    }

    public static void quit(final String input) {
        if (END.is(input)) {
            OutputView.quit();
            System.exit(0);
        }
    }

    public static void showStatus(final String input) {
        if (STATUS.is(input)) {
            OutputView.showStatus(judge);
        }
    }

    public static void move(final String input) {
        if (Objects.isNull(input) || input.trim().isEmpty()) {
            throw new InvalidInputException();
        }
        if (MOVE.is(input)) {
            tryToMove(MOVE.startPositionOf(input), MOVE.endPositionOf(input));
            OutputView.showBoard(board);
        }
    }

    public static void tryToMove(final Position start, final Position end) {
        try {
            board.move(start, end);
        } catch (InvalidInputException e) {
            OutputView.showError(e);
        }
    }
}
