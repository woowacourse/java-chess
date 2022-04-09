package chess.console;

import static chess.console.CommandType.END;
import static chess.console.CommandType.START;
import static chess.console.view.output.OutputView.printCurrentBoard;
import static chess.console.view.output.OutputView.printStartMessage;

import chess.console.view.input.InputView;
import chess.domain.board.Board;
import java.util.List;

public class ChessGame {

    private static final int COMMAND_TYPE_INDEX = 0;

    public void run() {
        printStartMessage();
        List<String> inputCommand = InputView.inputCommand();
        validateFirstCommand(findCommandType(inputCommand));
        Board board = Board.create();
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
        while (commandType != END && !board.hasOneKing());
    }
}
