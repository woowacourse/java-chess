package chess.controller.state;

import chess.domain.piece.Camp;

public class Ready extends State {
    public Ready() {
        super(Camp.WHITE);
    }

    @Override
    public Running start() {
        return new Running(turn());
    }

    @Override
    public End end() {
        return new End(turn());
    }

    @Override
    public State status() {
        throw new IllegalStateException("상태를 볼 수 없는 상태입니다.");
    }
}
