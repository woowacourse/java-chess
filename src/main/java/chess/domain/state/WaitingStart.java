package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class WaitingStart implements State {
    @Override
    public State start() {
        return new Running();
    }

    @Override
    public void move(final Square source, final Square target) {
        throw new IllegalStateException("보드 초기화 이전에 말을 움직일 수 없습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("보드 초기화 이전에 보드를 가져올 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
