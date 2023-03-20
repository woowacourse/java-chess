package chess.controller;

import static chess.controller.ExceptionHandler.repeatUntilValidInput;
import static chess.view.Command.END;
import static chess.view.Command.MOVE;
import static chess.view.Command.START;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printFinishMessage;
import static chess.view.OutputView.printGameStart;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.math.PositionConverter;
import chess.view.Command;
import chess.view.InputView;
import java.util.List;

public class MainController {

    private static final int COMMAND_INDEX = 0;
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_INDEX_POSITION = 2;
    private static final int ONLY_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final Board board;

    public MainController(final Board board) {
        this.board = board;
    }

    public void run() {
        printGameStart();
        List<String> inputs = repeatUntilValidInput(this::readValidCommand);
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        if (command == START) {
            printBoard(board.getBoard());
            while (repeatUntilValidInput(this::playChess));
        }
        printFinishMessage();
    }

    private boolean playChess() {
        List<String> inputs = repeatUntilValidInput(this::readValidCommand);
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        return executeCommand(inputs, command);
    }

    private boolean executeCommand(final List<String> inputs, final Command command) {
        if (command == END) {
            return false;
        }
        if (command == START) {
            throw new IllegalArgumentException("이미 게임을 실행중입니다. 다른 명령어를 입력해주세요.");
        }
        if (command == MOVE) {
            movePiece(inputs);
        }
        return true;
    }

    private void movePiece(final List<String> inputs) {
        Position current = PositionConverter.toPosition(inputs.get(CURRENT_POSITION_INDEX));
        Position target = PositionConverter.toPosition(inputs.get(TARGET_INDEX_POSITION));

        board.movePiece(current, target);
        printBoard(board.getBoard());
    }

    private List<String> readValidCommand() {
        List<String> inputs = InputView.readCommand();
        isValidCommand(inputs.get(COMMAND_INDEX));
        isValidInputSize(inputs);

        return inputs;
    }

    private void isValidCommand(final String commandValue) {
        try {
            Command.of(commandValue);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private void isValidInputSize(final List<String> inputs) {
        int size = inputs.size();

        if (size == ONLY_COMMAND_SIZE || size == MOVE_COMMAND_SIZE) {
            return;
        }
        throw new IllegalArgumentException("입력이 잘못되었습니다. 다시 입력해주세요.");
    }
}
