package chess;

import static chess.domain.Command.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.Judge;
import chess.domain.judge.WoowaJudge;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;
import chess.exceptions.InvalidInputException;

public class Application {
    private static final String DELIMITER = " ";
    private static Board board;
    private static Judge judge;

    public static void main(String[] args) {
        OutputView.instruction();
        while (true) {
            String command = InputView.getInput();
            validateCommand(command);
            if (END.is(command)) {
                OutputView.quit();
                break;
            }
            if (START.is(command)) {
                initialize();
            }
            if (STATUS.is(command)) {
                OutputView.showStatus(judge);
            }
            if (MOVE.is(command)) {
                move(command);
            }
            if (judge.isGameOver()) {
                OutputView.showGameOver(judge.winner());
                break;
            }
        }
    }

    public static void validateCommand(final String command) {
        try {
            validate(command);
        } catch (InvalidInputException e) {
            OutputView.showError(e);
        }
    }

    public static void initialize() {
        board = Board.init();
        judge = new WoowaJudge(board);
        OutputView.showBoard(board);
    }

    public static void move(final String command) {
        tryToMove(startPosition(command), endPosition(command));
        OutputView.showBoard(board);
    }

    public static void tryToMove(final Position start, final Position end) {
        try {
            board.move(start, end);
        } catch (InvalidInputException e) {
            OutputView.showError(e);
        }
    }

    private static Position startPosition(String command) {
        return Position.of(command.split(DELIMITER)[1]);
    }

    private static Position endPosition(String command) {
        return Position.of(command.split(DELIMITER)[2]);
    }
}
