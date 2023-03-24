package chess.controller.state;

import chess.domain.piece.Camp;

public class End extends State {
    End(final Camp turn) {
        super(turn);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public State status() {
        return this;
    }
}
