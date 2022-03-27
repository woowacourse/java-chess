package chess.domain.command;

import chess.domain.state.State;

public class Status extends Start {

    Status(State state) {
        super(state);
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public StatusResult getStatus() {
        return new StatusResult(state.getScore());
    }
}
