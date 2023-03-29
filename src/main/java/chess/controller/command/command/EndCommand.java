package chess.controller.command.command;

import chess.controller.ChessState;
import chess.dto.ChessGameDto;

import java.util.Set;

import static chess.controller.ChessState.END;
import static chess.controller.ChessState.PROGRESS;
import static chess.controller.ChessState.START;

public class EndCommand implements Command {

    private static final String CANNOT_END_BEFORE_START_ERROR_MESSAGE = "게임을 시작하기 전에 종료할 수 없습니다";

    private EndCommand() {
    }

    public static EndCommand create() {
        return new EndCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGameDto chessGameDto) {
        if (!Set.of(START, PROGRESS).contains(state)) {
            throw new IllegalArgumentException(CANNOT_END_BEFORE_START_ERROR_MESSAGE);
        }

        return END;
    }
}
