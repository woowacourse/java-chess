package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;

import static chess.controller.ChessState.*;

public class EndCommand implements StrategyCommand {

    private static final String CANNOT_END_BEFORE_START_ERROR_MESSAGE = "게임을 시작하기 전에 종료할 수 없습니다";

    private EndCommand() {
    }

    public static EndCommand create() {
        return new EndCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGame chessGame) {
        if (state == START || state == PROGRESS) {
            return END;
        }

        throw new IllegalArgumentException(CANNOT_END_BEFORE_START_ERROR_MESSAGE);
    }
}
