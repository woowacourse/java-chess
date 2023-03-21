package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;
import static chess.controller.Command.validateCommandSize;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final Board board = BoardFactory.create();
        OutputView.printGameStart();
        Command command = getInitCommand(board);
        while (command != END) {
            command = play(board);
        }
    }

    private Command getInitCommand(final Board board) {
        final Command command = createRepeatableInitCommand();
        if (command == START) {
            OutputView.printBoard(board.getBoard());
        }
        return command;
    }

    private Command createRepeatableInitCommand() {
        try {
            return Command.createInitCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return createRepeatableInitCommand();
        }
    }

    private Command play(final Board board) {
        try {
            List<String> commands = InputView.readMoveCommand();
            validateCommandSize(commands);
            Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
            return move(board, commands, command);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return play(board);
        }
    }

    private Command move(final Board board, final List<String> commands, final Command command) {
        if (command == MOVE) {
            board.move(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
            OutputView.printBoard(board.getBoard());
        }
        return command;
    }
}