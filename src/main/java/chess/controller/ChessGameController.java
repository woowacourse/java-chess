package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final Board board = BoardFactory.create();
        OutputView.printGameStart();
        Command command = getInitialCommand(board);
        while (command != END) {
            command = play(board);
        }
    }

    private Command getInitialCommand(final Board board) {
        final Command command = Command.getValidate(InputView.readCommand(), START, END);
        if (command == START) {
            OutputView.printBoard(board.getBoard());
        }
        return command;
    }

    private Command play(final Board board) {
        final List<String> commands = InputView.readMoveCommand();
        final Command command = Command.getValidate(commands.get(MOVE_COMMAND_INDEX), MOVE, END);
        if (command == MOVE) {
            board.move(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
            OutputView.printBoard(board.getBoard());
        }
        return command;
    }
}
