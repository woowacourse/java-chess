package chess.controller.state;

import chess.domain.piece.Camp;

public class KingDead extends State {
    KingDead(final Camp turn) {
        super(turn);
    }

    @Override
    public Running next() {
        throw new IllegalStateException("다음 차례를 진행할 수 없는 상태입니다.");
    }

    @Override
    public KingDead kingDead() {
        return this;
    }
}
