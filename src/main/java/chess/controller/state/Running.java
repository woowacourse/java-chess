package chess.controller.state;

import chess.domain.piece.Camp;

public class Running extends State {
    Running(final Camp turn) {
        super(turn);
    }

    @Override
    public State status() {
        return this;
    }

    @Override
    public Running next() {
        return new Running(Camp.nextTurn(turn()));
    }
}
