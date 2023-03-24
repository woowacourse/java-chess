package chess.controller.state;

import chess.domain.piece.Camp;

public abstract class State {
    private final Camp turn;

    State(final Camp turn) {
        this.turn = turn;
    }

    public Running start() {
        throw new IllegalStateException("게임을 시작할 수 없는 상태입니다.");
    }

    public Running next() {
        throw new IllegalStateException("다음 턴으로 넘길 수 없는 상태입니다.");
    }

    public State status() {
        throw new IllegalStateException("상태를 볼 수 없는 상태입니다.");
    }

    public End end() {
        return new End(turn);
    }

    public Camp turn() {
        return turn;
    }

    public boolean isRunning() {
        return true;
    }
}
