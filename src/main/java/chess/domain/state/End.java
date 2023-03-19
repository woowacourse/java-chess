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
        throw new UnsupportedOperationException("End 상태일 때 사용하지 않는 move 메서드 입니다.");
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("End 상태일 때 사용하지 않는 start 메서드 입니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("End 상태일 때 사용하지 않은 end 메서드 입니다.");
    }
}
