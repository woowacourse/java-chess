package chess;

import static chess.view.input.CommandType.END;
import static chess.view.input.CommandType.START;
import static chess.view.output.OutputView.printCurrentBoard;
import static chess.view.output.OutputView.printStartMessage;

import chess.domain.board.Board;
import chess.view.input.CommandType;
import chess.view.input.InputView;
import java.util.List;

public class ChessGame {

    private static final int COMMAND_TYPE_INDEX = 0;

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

    private void play(Board board) {
        CommandType commandType;
        do {
            printCurrentBoard(board);
            List<String> inputCommand = InputView.inputCommand();
            commandType = findCommandType(inputCommand);
            board = commandType.play(board, inputCommand);
        }
        while (commandType != END && !board.isCheckMate());
    }
}
