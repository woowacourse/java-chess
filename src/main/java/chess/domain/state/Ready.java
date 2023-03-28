package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;

public final class Ready extends State {
    public Ready(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException("start 명령어를 먼저 입력해 주세요");
    }

    @Override
    public State start() {
        return new Running(board(), color().reverse());
    }

    @Override
    public State end() {
        return new End(board(), color());
    }
}
