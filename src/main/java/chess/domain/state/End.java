package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;

public final class End extends State {
    public static final String COMMAND_NOT_USABLE_AFTER_END = "종료 후에 사용할 수 없는 명령어 입니다.";

    End(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException(COMMAND_NOT_USABLE_AFTER_END);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(COMMAND_NOT_USABLE_AFTER_END);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(COMMAND_NOT_USABLE_AFTER_END);
    }
}
