package chess.domain.game.state;

import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;

public final class End implements State {
    private static final String INVALID_COMMEND_MESSAGE = "게임이 종료되었습니다.";

    public End() {
    }

    @Override
    public State execute(CommandDto commandDto) {
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    public StateType getType() {
        return StateType.END;
    }
}
