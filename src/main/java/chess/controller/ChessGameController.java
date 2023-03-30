package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.LOAD;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.controller.Command.validateMoveCommandForm;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.BoardResult;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        Board board = BoardInitializer.initialize();
        OutputView.printGameStart();

        Command command = createInitCommand();
        board = updateBoard(command, board);
        while (command != END) {
            command = start(board);
        }

        OutputView.printResult(BoardResult.create(board.getBoard()));
    }

    private Command createInitCommand() {
        try {
            return Command.createInitCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return createInitCommand();
        }
    }

    private Board updateBoard(final Command command, Board board) {
        final ChessGameService chessGameService = new ChessGameService();
        if (command == START) {
            chessGameService.start();
        }
        if (command == LOAD) {
            board = chessGameService.load();
        }
        OutputView.printBoard(board.getBoard());

        return board;
    }

    private Command start(final Board board) {
        try {
            final List<String> commands = InputView.readMoveCommand();
            validateMoveCommandForm(commands);

            return play(board, commands);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return start(board);
        }
    }

    private Command play(final Board board, final List<String> commands) {
        Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
        if (command == STATUS) {
            OutputView.printScore(board.whiteScore(), board.blackScore());
        }
        if (command == MOVE) {
            command = move(board, commands);
        }

        return command;
    }

    private Command move(final Board board, final List<String> commands) {
        final ChessGameService chessGameService = new ChessGameService();
        final String source = commands.get(MOVE_SOURCE_INDEX);
        final String target = commands.get(MOVE_TARGET_INDEX);

        board.move(source, target);
        chessGameService.move(source, target);
        OutputView.printBoard(board.getBoard());

        return getCommandByBoardStatus(board);
    }

    private Command getCommandByBoardStatus(final Board board) {
        if (board.isKingDead()) {
            return END;
        }
        return MOVE;
    }
}