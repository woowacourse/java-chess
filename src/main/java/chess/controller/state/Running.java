package chess.controller.state;

import chess.domain.piece.Camp;

public class Running extends State {
    Running(final Camp turn) {
        super(turn);
    }

    @Override
    public Running start() {
        throw new IllegalStateException("게임을 시작할 수 없는 상태입니다.");
    }

    @Override
    public Running next() {
        return new Running(Camp.nextTurn(turn()));
    }

    @Override
    public End end() {
        return new End(turn());
    }
}
