package chess.domain.player;

import chess.domain.state.State;

public abstract class Player {
    private State state;

    protected Player(final State state) {
        this.state = state;
    }
}
