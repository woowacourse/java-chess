package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;

public final class End extends DefaultState {
    private static final String INVALID_COMMEND_MESSAGE = "게임이 종료되었습니다.";

    public End(ChessGame chessGame) {
        super(StateType.END, chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
