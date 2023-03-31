package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.dto.Command;

public final class Status extends Active {
    private static final long NOT_EXISTS_GAME_ID = 0L;

    private final Color current;

    public Status(final long gameId, final Chess chess, final Color current) {
        super(gameId, chess);
        this.current = current;
    }

    @Override
    public Active execute(final Command command) {
        validate();
        if (current.isWhite()) {
            return new White(gameId, chess);
        }
        return new Black(gameId, chess);
    }

    private void validate() {
        if (gameId == NOT_EXISTS_GAME_ID) {
            throw new IllegalArgumentException("게임이 시작되기 전에 상태를 확인할 수 없습니다.");
        }
    }
}
