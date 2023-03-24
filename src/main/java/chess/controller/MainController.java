package chess.controller;

import static chess.view.Command.END;
import static chess.view.Command.MOVE;
import static chess.view.Command.START;
import static chess.view.InputView.readCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printError;
import static chess.view.OutputView.printFinishMessage;
import static chess.view.OutputView.printGameStart;

import chess.domain.position.Position;
import chess.domain.board.Board;
import chess.domain.board.BoardMaker;
import chess.domain.math.PositionConverter;
import chess.view.Command;
import java.util.List;
import java.util.function.Supplier;

public final class MainController {

    private static final int COMMAND_INDEX = 0;
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int ONLY_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    public void run() {
        printGameStart();
        List<String> inputs = repeatUntilValidAction(this::readValidCommand);
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        if (command == START) {
            final Board board = new Board(new BoardMaker());
            printBoard(board.getBoard());
            while (repeatUntilValidAction(() -> playChess(board)));
        }
        printFinishMessage();
    }

    private <T> T repeatUntilValidAction(final Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
            return repeatUntilValidAction(reader);
        }
    }

    private List<String> readValidCommand() {
        List<String> inputs = readCommand();
        validateCommand(inputs.get(COMMAND_INDEX));
        validateInputSize(inputs);

        return inputs;
    }

    private boolean playChess(final Board board) {
        List<String> inputs = repeatUntilValidAction(this::readValidCommand);
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        return executeCommand(board, inputs, command);
    }

    private boolean executeCommand(final Board board, final List<String> inputs, final Command command) {
        if (command == END) {
            return false;
        }
        if (command == START) {
            throw new IllegalArgumentException("이미 게임을 실행중입니다. 다른 명령어를 입력해주세요.");
        }
        if (command == MOVE) {
            movePiece(board, inputs);
            printBoard(board.getBoard());
        }
        return true;
    }

    private void movePiece(final Board board, final List<String> inputs) {
        Position currentPosition = PositionConverter.toPosition(inputs.get(CURRENT_POSITION_INDEX));
        Position targetPosition = PositionConverter.toPosition(inputs.get(TARGET_POSITION_INDEX));

        board.movePiece(currentPosition, targetPosition);
    }

    private void validateCommand(final String commandValue) {
        try {
            Command.of(commandValue);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private void validateInputSize(final List<String> inputs) {
        int size = inputs.size();

        if (isValidInputSize(size)) {
            return;
        }
        throw new IllegalArgumentException("입력이 잘못되었습니다. 다시 입력해주세요.");
    }

    private boolean isValidInputSize(final int size) {
        return size == ONLY_COMMAND_SIZE || size == MOVE_COMMAND_SIZE;
    }
}
