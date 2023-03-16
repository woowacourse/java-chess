package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_COMMAND_SIZE;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private final ChessGame chessGame;
    private Command command;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
        this.command = Command.EMPTY;
    }

    public void run(final Retry retry) {
        initialize(retry);
        play(retry);
    }

    private void initialize(final Retry retry) {
        OutputView.printGameStart();
        while (command != Command.START) {
            command = readCommand(retry);
        }
        OutputView.printBoard(chessGame.getBoard());
    }

    private Command readCommand(Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return Command.createStart(InputView.readCommand());
            } catch (IllegalArgumentException e) {
                OutputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(Retry.RETRY_FAIL_MESSAGE);
    }

    private void play(final Retry retry) {
        while (command != END) {
            readMoveCommand(retry);
        }
    }

    private void readMoveCommand(Retry retry) {
        while (retry.isRepeatable()) {
            try {
                move(InputView.readMoveCommand());
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(Retry.RETRY_FAIL_MESSAGE);
    }

    private void move(final List<String> commands) {
        validateMoveCommandSize(commands);
        command = Command.createPlayOrEnd(commands.get(MOVE_COMMAND_INDEX));
        if (command == MOVE) {
            chessGame.move(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
            OutputView.printBoard(chessGame.getBoard());
        }
    }

    private void validateMoveCommandSize(final List<String> commands) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("올바른 커맨드를 입력해주세요.");
        }
    }
}
