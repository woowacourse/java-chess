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
import chess.dto.MoveDto;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final ChessGameService chessGameService = new ChessGameService();
        Board board = BoardInitializer.initialize();
        OutputView.printGameStart();

        Command command = getInitCommand(chessGameService, board);
        board = checkLoadCommand(chessGameService, board, command);
        while (command != END) {
            command = start(chessGameService, board);
        }

        OutputView.printResult(BoardResult.create(board.getBoard()));
    }

    private Command getInitCommand(final ChessGameService chessGameService, final Board board) {
        final Command command = createInitCommand();
        if (command == START) {
            chessGameService.clear();
        }

        return command;
    }

    private Board checkLoadCommand(final ChessGameService chessGameService, Board board, final Command command) {
        if (command == LOAD) {
            board = loadStoredBoard(chessGameService, board);
            OutputView.printBoard(board.getBoard());
        }

        return board;
    }


    private Board loadStoredBoard(final ChessGameService chessGameService, final Board board) {
        final List<MoveDto> logs = chessGameService.load();
        for (MoveDto moveDto : logs) {
            final String source = moveDto.getSource();
            final String target = moveDto.getTarget();

            board.move(source, target);
        }

        return board;
    }

    private Command createInitCommand() {
        try {
            return Command.createInitCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return createInitCommand();
        }
    }

    private Command start(final ChessGameService chessGameService, final Board board) {
        try {
            final List<String> commands = InputView.readMoveCommand();
            validateMoveCommandForm(commands);

            return play(chessGameService, board, commands);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return start(chessGameService, board);
        }
    }

    private Command play(final ChessGameService chessGameService, final Board board, final List<String> commands) {
        Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
        if (command == STATUS) {
            OutputView.printScore(board.whiteScore(), board.blackScore());
        }
        if (command == MOVE) {
            command = move(chessGameService, board, commands);
        }

        return command;
    }

    private Command move(final ChessGameService chessGameService, final Board board, final List<String> commands) {
        final String source = commands.get(MOVE_SOURCE_INDEX);
        final String target = commands.get(MOVE_TARGET_INDEX);

        chessGameService.move(source, target);
        board.move(source, target);
        OutputView.printBoard(board.getBoard());

        if (board.isKingDead()) {
            return END;
        }
        return MOVE;
    }
}
