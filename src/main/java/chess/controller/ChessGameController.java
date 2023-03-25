package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.controller.Command.validateMoveCommandForm;

import chess.domain.board.BoardResult;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final ChessGameService chessGameService = new ChessGameService();
        OutputView.printGameStart();

        Command command = getInitCommand(chessGameService);
        while (command != END) {
            command = start(chessGameService);
        }

        OutputView.printResult(BoardResult.create(chessGameService.board()));
    }

    private Command getInitCommand(final ChessGameService chessGameService) {
        final Command command = createInitCommand();
        if (command == START) {
            OutputView.printBoard(chessGameService.board());
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

    private Command start(final ChessGameService chessGameService) {
        try {
            final List<String> commands = InputView.readMoveCommand();
            validateMoveCommandForm(commands);

            return play(chessGameService, commands);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            return start(chessGameService);
        }
    }

    private Command play(final ChessGameService chessGameService, final List<String> commands) {
        Command command = Command.createPlayingCommand(commands.get(MOVE_COMMAND_INDEX));
        if (command == STATUS) {
            OutputView.printScore(chessGameService.whiteScore(), chessGameService.blackScore());
        }
        if (command == MOVE) {
            command = move(chessGameService, commands);
        }

        return command;
    }

    private Command move(final ChessGameService chessGameService, final List<String> commands) {
        final String source = commands.get(MOVE_SOURCE_INDEX);
        final String target = commands.get(MOVE_TARGET_INDEX);

        chessGameService.move(source, target);
        OutputView.printBoard(chessGameService.board());

        if (chessGameService.isKingDead()) {
            return END;
        }
        return MOVE;
    }
}
