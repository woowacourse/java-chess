package chess;

import static chess.Command.END;
import static chess.Command.MOVE;
import static chess.Command.MOVE_COMMAND_INDEX;
import static chess.Command.MOVE_SOURCE_INDEX;
import static chess.Command.MOVE_TARGET_INDEX;
import static chess.Command.START;
import static chess.Command.STATUS;
import static chess.Command.validateMoveCommandForm;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.BoardResult;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameApplication {
    public static void main(String[] args) {
        try {
            run();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
        }
    }

    public static void run() {
        final Board board = BoardInitializer.initialize();
        OutputView.printGameStart();

        Command command = getInitCommand(board);
        while (command != END) {
            command = start(board);
        }

        OutputView.printResult(BoardResult.create(board.getBoard()));
    }

    private static Command getInitCommand(final Board board) {
        final Command command = createInitCommand();
        if (command == START) {
            OutputView.printBoard(board.getBoard());
        }

        return command;
    }

    private static Command createInitCommand() {
        try {
            return Command.createInitCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return createInitCommand();
        }
    }

    private static Command start(final Board board) {
        try {
            final List<String> commands = InputView.readMoveCommand();
            validateMoveCommandForm(commands);

            return play(board, commands);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return start(board);
        }
    }

    private static Command play(final Board board, final List<String> commands) {
        Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
        if (command == STATUS) {
            OutputView.printScore(board.whiteScore(), board.blackScore());
        }
        if (command == MOVE) {
            command = move(board, commands);
        }

        return command;
    }

    private static Command move(final Board board, final List<String> commands) {
        final String source = commands.get(MOVE_SOURCE_INDEX);
        final String target = commands.get(MOVE_TARGET_INDEX);

        board.move(source, target);
        OutputView.printBoard(board.getBoard());

        if (board.isKingDead()) {
            return END;
        }
        return MOVE;
    }
}
