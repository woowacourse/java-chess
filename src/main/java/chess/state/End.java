package chess.state;

import chess.controller.ChessService;
import chess.model.GameStartCommand;
import java.util.List;

public final class End implements GameState {

    private static final String END_CANNOT_CHANGE_STATUS_ERROR_MESSAGE = "잘못된 상태 입력입니다.";

    @Override
    public GameState changeStatus(GameStartCommand command) {
        throw new IllegalArgumentException(END_CANNOT_CHANGE_STATUS_ERROR_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public GameState execute(ChessService service, List<String> squares) {
        return this;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
