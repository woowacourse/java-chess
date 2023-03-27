package chess.controller.command.command;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

import java.util.Set;

import static chess.controller.ChessState.INIT;
import static chess.controller.ChessState.START;

public class StartCommand implements Command {

    private static final String CANNOT_START_AFTER_START_ERROR_MESSAGE = "게임 진행 도중에 다시 초기화할 수 없습니다.";

    private StartCommand() {
    }

    public static StartCommand create() {
        return new StartCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGame chessGame) {
        if (!Set.of(INIT).contains(state)) {
            throw new IllegalArgumentException(CANNOT_START_AFTER_START_ERROR_MESSAGE);
        }

        OutputView.printBoard(chessGame.getBoard());
        return START;
    }
}
