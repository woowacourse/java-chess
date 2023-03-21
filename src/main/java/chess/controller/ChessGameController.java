package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;
import static chess.controller.Command.validateMoveCommandForm;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final Board board = BoardFactory.create();
        OutputView.printGameStart();
        Command command = getInitCommand(board);
        while (command != END) {
            command = start(board);
        }
    }

    private Command getInitCommand(final Board board) {
        final Command command = createInitCommand();
        if (command == START) {
            OutputView.printBoard(board.getBoard());
        }
        return command;
    }

    private Command createInitCommand() {
        try {
            return Command.createInitCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return createInitCommand();
        }
    }

    private Command start(final Board board) {
        try {
            List<String> commands = InputView.readMoveCommand();
            validateMoveCommandForm(commands);
            return play(board, commands);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return start(board);
        }
    }

    private Command play(final Board board, final List<String> commands) {
        Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
        if (command == MOVE) {
            move(board, commands);
        }
        return command;
    }

    private void move(final Board board, final List<String> commands) {
        final String source = commands.get(MOVE_SOURCE_INDEX);
        final String target = commands.get(MOVE_TARGET_INDEX);
        board.move(source, target);
        OutputView.printBoard(board.getBoard());
    }
}