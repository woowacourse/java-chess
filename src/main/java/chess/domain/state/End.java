package chess.domain.state;

import chess.domain.Board;
import chess.domain.Position;

public class End extends State {
    protected End(final Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }
}
