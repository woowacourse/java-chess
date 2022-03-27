package chess;

import static chess.view.input.CommandType.END;
import static chess.view.input.CommandType.START;
import static chess.view.output.OutputView.printCurrentBoard;
import static chess.view.output.OutputView.printStartMessage;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.input.CommandType;
import chess.view.input.InputView;
import java.util.List;

public class ChessGame {

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        printStartMessage();
        List<String> inputCommand = InputView.inputCommand();
        validateFirstCommand(findCommandType(inputCommand));
        Board board = new Board();
        play(board);
    }

    private CommandType findCommandType(final List<String> command) {
        return CommandType.from(command.get(COMMAND_TYPE_INDEX));
    }

    private void validateFirstCommand(final CommandType commandType) {
        if (commandType != START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void play(final Board board) {
        printCurrentBoard(board);
        final List<String> inputCommand = InputView.inputCommand();
        final CommandType commandType = findCommandType(inputCommand);

        if (commandType == END || board.isCheckMate()) {
            return;
        }
        validateWrongCommandType(commandType);
        play(movePiece(board, inputCommand));
    }

    private void validateWrongCommandType(final CommandType commandType) {
        if (commandType == START) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }

    private Board movePiece(final Board board, final List<String> command) {
        final Position sourcePosition = Position.from(command.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = Position.from(command.get(TARGET_POSITION_INDEX));
        return board.movePiece(sourcePosition, targetPosition);
    }
}
